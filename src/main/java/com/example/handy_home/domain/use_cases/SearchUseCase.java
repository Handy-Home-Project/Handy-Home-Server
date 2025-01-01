package com.example.handy_home.domain.use_cases;

import com.example.handy_home.data.repositories.NaverRealtyRepository;
import com.example.handy_home.common.dto.ComplexDTO;
import com.example.handy_home.common.dto.ComplexDetailDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SearchUseCase {

    private final NaverRealtyRepository naverRealtyRepository;

    public SearchUseCase(NaverRealtyRepository naverRealtyRepository) {
        this.naverRealtyRepository = naverRealtyRepository;
    }

    public List<ComplexDTO> getSearchSuggestions(String keyword) {
        final List<Map<String, Object>> complexes = naverRealtyRepository.getKeywordComplexes(keyword);

        return complexes.stream().map(ComplexDTO::fromJson).collect(Collectors.toList());
    }

    public ComplexDetailDTO getComplexDetails(String complexNo) {
        final Map<String, Object> complexDetails = naverRealtyRepository.getAreaListFromComplexNo(complexNo);

        return ComplexDetailDTO.fromJson(complexDetails);
    }
}
