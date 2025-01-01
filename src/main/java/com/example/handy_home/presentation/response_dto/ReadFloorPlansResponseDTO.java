package com.example.handy_home.presentation.response_dto;

import com.example.handy_home.common.dto.ComplexDetailDTO;
import com.example.handy_home.common.utils.Status;

public class ReadFloorPlansResponseDTO extends ResponseDTO<ComplexDetailDTO> {
    public ReadFloorPlansResponseDTO(ComplexDetailDTO body) {
        super(Status.S0000, true, body);
    }
}

