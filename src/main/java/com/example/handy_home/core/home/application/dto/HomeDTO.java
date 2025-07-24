package com.example.handy_home.core.home.application.dto;

import com.example.handy_home.core.home.domain.Home;
import com.example.handy_home.core.user.application.dto.UserDTO;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public record HomeDTO(
        Long id,
        String name,
        List<RoomDTO> roomList,
        UserDTO user,
        Boolean isPreview,
        Long sourceId
        )
{
    // FIXME : Gson 사용은 Util 기능, 별도로 분리해야 함
    public static HomeDTO fromEntity(Home home) {
        List<RoomDTO> roomDTOList = home.getRooms()
                .stream()
                .map(room -> new RoomDTO(room.getName(), room.getVertexesJson(), room.getType().name()))
                .collect(Collectors.toList());
        return new HomeDTO(
                home.getId(),
                home.getName(),
                roomDTOList,
                new UserDTO(home.getUser().getId(), home.getUser().getName(),""),
                home.isPreview(),
                home.getSourceId()
                );
    }
}
