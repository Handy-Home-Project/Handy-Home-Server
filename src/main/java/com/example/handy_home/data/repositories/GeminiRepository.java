package com.example.handy_home.data.repositories;

import com.example.handy_home.common.dto.GeminiRequestDTO;
import com.google.gson.Gson;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Repository
public class GeminiRepository {

    private static final String GEMINI_BASE_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent";

    private final RestTemplate restTemplate = new RestTemplate();

    private final Environment env;

    public GeminiRepository(Environment env) {
        this.env = env;
    }

    public Map<String, Object> generateStringFromBase64Image(String base64Image, String mimeType, String prompt) {
        try {

            final String apiKey = env.getProperty("gemini.api.key");

            final String url = STR."\{GEMINI_BASE_API_URL}?key=\{apiKey}";

            final Gson gson = new Gson();

            final List<GeminiRequestDTO.Content.Part> parts = List.of(
                    new GeminiRequestDTO.Content.Part(prompt, null),
                    new GeminiRequestDTO.Content.Part(null, new GeminiRequestDTO.Content.Part.InlineData(mimeType, base64Image))
            );

            final String body = gson.toJson(new GeminiRequestDTO(List.of(new GeminiRequestDTO.Content(parts))));

            final HttpHeaders headers = new HttpHeaders();

            headers.add("Content-Type", "application/json");

            final HttpEntity<String> entity = new HttpEntity<>(body, headers);
            final ResponseEntity<Map<String, Object>> response = restTemplate.exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<>(){});

            return response.getBody();
        } catch (Exception _) {
            return null;
        }
    }
}