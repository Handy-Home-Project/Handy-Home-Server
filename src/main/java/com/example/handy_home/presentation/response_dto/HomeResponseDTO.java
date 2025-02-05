package com.example.handy_home.presentation.response_dto;

import com.example.handy_home.common.dto.HomeDTO;
import com.example.handy_home.common.dto.HomeDetailDTO;
import com.example.handy_home.common.dto.UserDTO;
import com.example.handy_home.common.utils.Status;
import com.example.handy_home.domain.entities.UserEntity;

public class HomeResponseDTO extends ResponseDTO<HomeDetailDTO> {

    public HomeResponseDTO(HomeDetailDTO homeDetail) {
        super(Status.S0000, true, homeDetail);
    }
}
