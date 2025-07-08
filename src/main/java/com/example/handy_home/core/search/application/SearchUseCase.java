package com.example.handy_home.core.search.application;

import com.example.handy_home.core.search.application.dto.ComplexDTO;
import com.example.handy_home.core.search.application.dto.ComplexDetailDTO;

import java.util.List;

public interface SearchUseCase {
    List<ComplexDTO> getSearchSuggestions(String keyword);
    ComplexDetailDTO getComplexDetails(String complexNo);
}
