package com.example.handy_home.infrastructure.realestate.naver;


import com.example.handy_home.core.search.domain.SearchService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class NaverRealEstateSearchServiceImpl implements SearchService {

    private static final String NAVER_REALTY_BASE_API_URL = "https://new.land.naver.com/api";

    private static String naverRealtyToken;

    public static void setNaverRealtyToken(String token) {
        token.replace("Bearer ", "");
        naverRealtyToken = token;
    }

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Map<String, Object>> getKeywordComplexes(String keyword) {
        try {

            // API 호출
            String url = String.format("%s/search?keyword=%s&page=1", NAVER_REALTY_BASE_API_URL, keyword);
            final ResponseEntity<Map<String, Object>> response = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<>() {});

            return (List<Map<String, Object>>) response.getBody().get("complexes");
        } catch (Exception e) {
            return null;
        }
    }

    public Map<String, Object> getAreaListFromComplexNo(String complexNo) {

        String url = String.format("%s/complexes/%s?sameAddressGroup=false", NAVER_REALTY_BASE_API_URL, complexNo);
        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", naverRealtyToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        final ResponseEntity<Map<String, Object>> response = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {});

        return response.getBody();
    }

}
