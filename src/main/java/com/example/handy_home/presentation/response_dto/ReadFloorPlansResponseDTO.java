package com.example.handy_home.presentation.response_dto;

import com.example.handy_home.core.search.application.dto.ComplexDetailDTO;

public class ReadFloorPlansResponseDTO extends ResponseDTO<ComplexDetailDTO> {
    public ReadFloorPlansResponseDTO(ComplexDetailDTO body) {
        super(Status.S0000, true, body);
    }
}

