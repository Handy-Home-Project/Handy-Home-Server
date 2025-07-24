package com.example.handy_home.core.user.application.dto;

import com.example.handy_home.core.user.domain.User;

public record UserDTO(String id, String name, String password) {
    public static UserDTO fromEntity(User user) {
        return new UserDTO(user.getId(), user.getName(),"");
    }
}
