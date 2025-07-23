package com.example.handy_home.core.home.application;

import com.example.handy_home.core.home.application.dto.RoomDTO;
import com.example.handy_home.core.home.domain.Room;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;

@Component
@RequiredArgsConstructor
public class HomeGenerator {

    @Value("${fast-api.url}")
    private String fastApiUrl;
    private final Environment env;
    private RestTemplate restTemplate;
    private ObjectMapper mapper = new ObjectMapper();
    private String requestRoomJson(File image) {
        if(restTemplate == null) {
            restTemplate = new RestTemplate();
        }
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        FileSystemResource imageResource = new FileSystemResource(image);
        body.add("floor_plan_image", imageResource);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        return restTemplate.postForEntity(fastApiUrl, requestEntity, String.class).getBody();
    }

    public JsonNode generate(File image) throws JsonProcessingException {
        String json = requestRoomJson(image);
        return mapper.readTree(json);
    }
}
