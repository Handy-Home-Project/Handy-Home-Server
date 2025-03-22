package com.example.handy_home.domain.use_cases;

import com.example.handy_home.common.dto.AnalyzeInteriorDTO;
import com.example.handy_home.data.models.furniture.Color;
import com.example.handy_home.data.models.furniture.Style;
import com.example.handy_home.data.repositories.GeminiRepository;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Service
public class InteriorUseCase {

    private final GeminiRepository geminiRepository;

    public InteriorUseCase(GeminiRepository geminiRepository) {
        this.geminiRepository = geminiRepository;
    }

    public AnalyzeInteriorDTO getAnalyzeInteriorFromImageUrl(byte[] image, String mimeType) {

        String colors = Stream.of(Color.values()).map(Enum::toString).toList().toString();
        String style = Stream.of(Style.values()).map(Enum::toString).toList().toString();
        final String prompt = STR."""
Analyze an interior image and create an AnalyzeInterior, then return it as a string. The AnalyzeInterior should have two keys: style and colors. The value of the style key must be one of the values from the Style enum. The value of the colors key must be a list containing the top three most dominant colors from the image, selected from the Colors enum.

Style and Colors enums are defined as follows:
Style enum: \{style}
Colors enum: \{colors}

The return format must include no additional sentences and should be as follows:
{
"style": "",
"colors": []
}
""";

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

}