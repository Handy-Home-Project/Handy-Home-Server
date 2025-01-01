package com.example.handy_home.data.repositories;


import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Repository
public class NaverRealtyRepository {

    private static final String NAVER_REALTY_BASE_API_URL = "https://new.land.naver.com/api";

    private static String naverRealtyToken;

    public static void setNaverRealtyToken(String token) {
        System.out.println(token);
        naverRealtyToken = token;
    }

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Map<String, Object>> getKeywordComplexes(String keyword) {
        try {

            // API 호출
            final String url = STR."\{NAVER_REALTY_BASE_API_URL}/search?keyword=\{keyword}&page=1";
            final ResponseEntity<Map<String, Object>> response = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<>() {});

            return (List<Map<String, Object>>) response.getBody().get("complexes");
        } catch (Exception _) {
            return null;
        }
    }

    public Map<String, Object> getAreaListFromComplexNo(String complexNo) {
        final String url = STR."\{NAVER_REALTY_BASE_API_URL}/complexes/\{complexNo}?sameAddressGroup=false";
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(naverRealtyToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        final ResponseEntity<Map<String, Object>> response = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {});

        return response.getBody();
    }

}
