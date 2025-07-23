package com.example.handy_home.core.home.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public record RoomDTO(
        String name,
        String vertexes,
        String type
) {}
