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

public record HomeDTO(
        Long id,
        List<RoomDTO> roomList,
        UserDTO user)
{
    // FIXME : Gson 사용은 Util 기능, 별도로 분리해야 함
    public static HomeDTO fromEntity(Home home) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(home.getLayoutData(), JsonObject.class);

        Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();

        List<RoomDTO> roomDTOList = new ArrayList<>();

        for (Map.Entry<String, JsonElement> entry : entrySet) {
            String roomName = entry.getKey();
            List<List<Long>> vertexes = convertToVertexList(entry.getValue());

            RoomDTO roomDTO = new RoomDTO(roomName, vertexes);
            roomDTOList.add(roomDTO);
        }
        return new HomeDTO(home.getId(), roomDTOList, new UserDTO(home.getUser().getId(), home.getUser().getName(),""));
    }

    private static List<List<Long>> convertToVertexList(JsonElement jsonElement) {
        List<List<Long>> vertexes = new ArrayList<>();

        for (JsonElement vertexArray : jsonElement.getAsJsonArray()) {
            List<Long> vertex = new ArrayList<>();
            for (JsonElement coordinate : vertexArray.getAsJsonArray()) {
                vertex.add(coordinate.getAsLong());
            }
            vertexes.add(vertex);
        }

        return vertexes;
    }
}
