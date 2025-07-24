package com.example.handy_home.core.home.application.dto;

import com.example.handy_home.core.home.domain.Home;
import com.example.handy_home.core.home.domain.Room;
import com.example.handy_home.core.home.domain.emums.RoomType;
import com.example.handy_home.core.user.application.dto.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

public record RoomDTO(
        String name,
        String vertexes,
        String type
) {
    // FIXME : Gson 사용은 Util 기능, 별도로 분리해야 함
    public static RoomDTO fromEntity(Room room) {
        return new RoomDTO(room.getName(), room.getVertexesJson(), room.getType().name());
    }
}
