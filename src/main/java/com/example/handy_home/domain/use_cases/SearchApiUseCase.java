package com.example.handy_home.domain.use_cases;

import com.example.handy_home.presentation.dto.response.SearchResultDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearchApiUseCase {

    private static final String SEARCH_API_URL = "https://new.land.naver.com/api/search";

    public List<SearchResultDTO> searchComplexes(String keyword) {
        RestTemplate restTemplate = new RestTemplate();

        // API 호출
        String url = SEARCH_API_URL + "?keyword=" + keyword + "&page=1";
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        List<SearchResultDTO> results = new ArrayList<>();

        if (response != null && response.get("complexes") != null) {
            List<Map<String, Object>> complexes = (List<Map<String, Object>>) response.get("complexes");

            for (Map<String, Object> complex : complexes) {
                // 데이터 추출
                String complexNo = (String) complex.get("complexNo");
                String title = (String) complex.get("complexName");
                String address = (String) complex.get("cortarAddress");
                String link = (String) complex.get("deepLink");

                // DTO로 매핑
                results.add(new SearchResultDTO(complexNo, title, address, link));
            }
        }

        return results;
    }
}
