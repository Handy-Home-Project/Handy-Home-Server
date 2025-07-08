package com.example.handy_home.core.home.application.dto;

import com.example.handy_home.core.user.application.dto.UserDTO;

public record HomeDetailDTO(HomeDTO home, UserDTO user) {
}
