package com.example.handy_home.presentation.response_dto;

import com.example.handy_home.common.dto.ComplexDTO;
import com.example.handy_home.common.utils.Status;

import java.util.List;

public class ReadSearchSuggestionsResponseDTO extends ResponseDTO<List<ComplexDTO>> {

    public ReadSearchSuggestionsResponseDTO(List<ComplexDTO> body) {
        super(Status.S0000, true, body);
    }
}
