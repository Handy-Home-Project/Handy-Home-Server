package com.example.handy_home.presentation.response_dto;

import com.example.handy_home.core.search.application.dto.ComplexDTO;

import java.util.List;

public class ReadSearchSuggestionsResponseDTO extends ResponseDTO<List<ComplexDTO>> {

    public ReadSearchSuggestionsResponseDTO(List<ComplexDTO> body) {
        super(Status.S0000, true, body);
    }
}
