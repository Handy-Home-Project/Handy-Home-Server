package com.example.handy_home.core.home.application.dto;

import java.util.List;

public record RoomDTO(
        String roomName,
        List<List<Long>> vertexes
) {}
