package com.example.handy_home.core.search.application;

import com.example.handy_home.core.search.application.dto.ComplexDTO;
import com.example.handy_home.core.search.application.dto.ComplexDetailDTO;
import com.example.handy_home.core.search.domain.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class SearchDefaultService implements SearchUseCase {
    private final SearchService searchService;

    public List<ComplexDTO> getSearchSuggestions(String keyword) {
        final List<Map<String, Object>> complexes = searchService.getKeywordComplexes(keyword);

        if (complexes == null) return new ArrayList<>();

        return complexes.stream().map(ComplexDTO::fromJson).collect(Collectors.toList());
    }

    public ComplexDetailDTO getComplexDetails(String complexNo) {
        final Map<String, Object> complexDetails = searchService.getAreaListFromComplexNo(complexNo);

        return ComplexDetailDTO.fromJson(complexDetails);
    }
}
