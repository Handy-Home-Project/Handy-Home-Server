package com.example.handy_home.common.dto;

import com.example.handy_home.domain.entities.UserEntity;

public record UserDTO(Long id, String name) {

    public static UserDTO fromUserEntity(UserEntity user) {
        return new UserDTO(user.id(), user.name());
    }
}
