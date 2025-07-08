package com.example.handy_home.presentation.response_dto;

import com.example.handy_home.core.home.application.dto.HomeDetailDTO;

public class HomeResponseDTO extends ResponseDTO<HomeDetailDTO> {

    public HomeResponseDTO(HomeDetailDTO homeDetail) {
        super(Status.S0000, true, homeDetail);
    }
}
