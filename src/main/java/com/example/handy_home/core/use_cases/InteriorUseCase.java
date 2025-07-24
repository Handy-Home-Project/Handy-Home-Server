package com.example.handy_home.core.use_cases;

import com.example.handy_home.common.dto.AnalyzeInteriorDTO;
import com.example.handy_home.core.home.application.dto.HomeDTO;
import com.example.handy_home.core.home.application.dto.RoomDTO;
import com.example.handy_home.core.interior.application.dto.SampleFurnitureDto;
import com.example.handy_home.core.interior.domain.enums.Color;
import com.example.handy_home.core.interior.domain.enums.Style;
import com.example.handy_home.infrastructure.data.GeminiRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;


/*
* 인테리어 분석
* Gemini 요청에 사용
* */
@Service
@Log4j2
public class InteriorUseCase {
    private ObjectMapper mapper = new ObjectMapper();
    private final GeminiRepository geminiRepository;

    public InteriorUseCase(GeminiRepository geminiRepository) {
        this.geminiRepository = geminiRepository;
    }

    public AnalyzeInteriorDTO getAnalyzeInteriorFromImageUrl(byte[] image, String mimeType) {

        String colors = Stream.of(Color.values()).map(Enum::toString).toList().toString();
        String style = Stream.of(Style.values()).map(Enum::toString).toList().toString();
        final String prompt = String.format("""
Analyze an interior image and create an AnalyzeInterior, then return it as a string. The AnalyzeInterior should have two keys: style and colors. The value of the style key must be one of the values from the Style enum. The value of the colors key must be a list containing the top three most dominant colors from the image, selected from the Colors enum.

Style and Colors enums are defined as follows:
Style enum: %s
Colors enum: %s

The return format must include no additional sentences and should be as follows:
{
"style": "",
"colors": []
}
""", style, colors);

        final Base64.Encoder encoder = Base64.getEncoder (); // java.util.Base64.Encoder
        final String base64Image = encoder.encodeToString(image);

        final Map<String, Object> response = geminiRepository.generateStringFromBase64Image(base64Image, mimeType, prompt);

        try {
            System.out.println(response);
            Map<String, Object> firstCandidate = ((List<Map<String, Object>>) response.get("candidates")).get(0);
            Map<String, Object> content = (Map<String, Object>) firstCandidate.get("content");
            Map<String, Object> firstPart = ((List<Map<String, Object>>) content.get("parts")).get(0);
            String text = (String) firstPart.get("text");

            String regex = "\\{[^}]*\\}";

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);

            if (matcher.find()) {
                String match = matcher.group();
                final Gson gson = new Gson();

                return gson.fromJson(match, AnalyzeInteriorDTO.class);
            } else {
                return null;
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void getSuggestionInterior(String homeJson, List<SampleFurnitureDto> furnitureList)  {

        try {
            HomeDTO home = mapper.readValue(homeJson, HomeDTO.class);
            String roomVertexes = getRoomVertexes(home.roomList());
            String furnitures = mapper.writeValueAsString(furnitureList);

            final String prompt = String.format("""
Analyze the room and furniture layout based on the given RoomVertexes and Furnitures, and recommend optimal furniture placement positions within the room coordinates.

RoomVertexes and Furnitures are provided as follows:
Room : %s
Furniture : %s

Return the recommended furniture layout as a JSON string in the following format:
{
    "FurniturePlacement": [
        {
            "id": "furniture_id",
            "name": "furniture_name",
            "position": { "x": float, "y": float },
            "rotation": float
        },
        ...
    ]
}

Only return the JSON without any additional text.
""", roomVertexes, furnitures);


            final Map<String, Object> response = geminiRepository.getSuggestionsLocation(prompt);
            System.out.println(response);
            Map<String, Object> firstCandidate = ((List<Map<String, Object>>) response.get("candidates")).get(0);
            Map<String, Object> content = (Map<String, Object>) firstCandidate.get("content");
            Map<String, Object> firstPart = ((List<Map<String, Object>>) content.get("parts")).get(0);
            String text = (String) firstPart.get("text");
            text = text.replaceAll("```json", "");
            text = text.replaceAll("```", "");

            JsonNode rootNode = mapper.readTree(text);

            JsonNode furniturePlacementNode = rootNode.get("FurniturePlacement");
            List<FurniturePlacement> result = mapper.readerForListOf(FurniturePlacement.class)
                    .readValue(furniturePlacementNode);
            log.info(result);

        } catch (JsonSyntaxException | JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    private String getRoomVertexes(List<RoomDTO> roomList) throws JsonProcessingException {

        ArrayNode arrayNode = mapper.createArrayNode();
        for (RoomDTO room : roomList) {
            String vertexesJson = room.vertexes();  // vertexes 필드가 JSON 배열 문자열이라고 가정

            if (vertexesJson != null && !vertexesJson.isEmpty()) {
                // 문자열을 JsonNode로 파싱
                JsonNode vertexesNode = mapper.readTree(vertexesJson);

                // vertexesNode가 배열인지 확인 후 배열에 추가
                if (vertexesNode.isArray()) {
                    arrayNode.add(vertexesNode);
                } else {
                    // 배열이 아니라면 필요하면 처리: 예) 무시 또는 예외
                    System.err.println("vertexes 필드가 배열이 아님: " + vertexesJson);
                }
            }
        }
        return mapper.writeValueAsString(arrayNode);
    }

    @Getter
    @Setter
    @ToString
    public static class FurniturePlacement {
        String id;
        String name;
        Position position;
        int rotation;
    }

    @Getter
    @Setter
    @ToString
    public static class Position {
        int x;
        int y;
    }
}