package com.example.handy_home.core.user.application;

import com.example.handy_home.core.user.application.dto.UserDTO;

public interface UserService {
    UserDTO join(UserDTO userDTO);
    UserDTO login(String id, String password);
}
