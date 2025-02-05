package com.example.handy_home.common.dto;

import com.example.handy_home.domain.entities.HomeEntity;

import java.util.List;

public record HomeDTO(Long id, List<RoomDTO> roomList) {

    public static HomeDTO fromHomeEntity(HomeEntity home) {
        return new HomeDTO(home.id(), home.roomList());
    }
}
