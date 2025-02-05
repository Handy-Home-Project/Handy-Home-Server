package com.example.handy_home.common.dto;

import com.example.handy_home.domain.entities.UserEntity;

import java.util.List;

public record RoomDTO(String roomName, List<List<Long>> vertexes) {
}
