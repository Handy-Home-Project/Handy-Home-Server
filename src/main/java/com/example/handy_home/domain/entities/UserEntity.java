package com.example.handy_home.domain.entities;

import com.example.handy_home.data.models.UserModel;

public record UserEntity(Long id, String name) {

    public UserEntity {
        if (!isValidUserName(name)) {
            throw new IllegalArgumentException("Invalid user name");
        }
    }

    public static UserEntity fromUserModel(UserModel model) {
        return new UserEntity(model.getId(), model.getName());
    }

    public static boolean isValidUserName(String name) {
        return name != null && name.length() <= 5;
    }
}
