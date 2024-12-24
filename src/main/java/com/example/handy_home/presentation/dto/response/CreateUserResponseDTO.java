package com.example.handy_home.presentation.dto.response;

import com.example.handy_home.common.utils.Status;
import com.example.handy_home.domain.entities.UserEntity;
import com.example.handy_home.presentation.dto.UserDTO;

public class CreateUserResponseDTO extends ResponseDTO<UserDTO> {

    public CreateUserResponseDTO(UserEntity user) {
        super(Status.S0000, true, UserDTO.fromUserEntity(user));
    }
}
