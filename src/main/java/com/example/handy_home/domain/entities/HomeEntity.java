package com.example.handy_home.domain.entities;

import com.example.handy_home.common.dto.RoomDTO;
import com.example.handy_home.data.models.HomeModel;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public record HomeEntity(Long id, List<RoomDTO> roomList) {
    public static HomeEntity fromHomeModel(HomeModel model) {

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(model.getHomeJson(), JsonObject.class);

        Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();

        List<RoomDTO> roomDTOList = new ArrayList<>();

        for (Map.Entry<String, JsonElement> entry : entrySet) {
            String roomName = entry.getKey();
            List<List<Long>> vertexes = convertToVertexList(entry.getValue());

            RoomDTO roomDTO = new RoomDTO(roomName, vertexes);
            roomDTOList.add(roomDTO);
        }

        return new HomeEntity(model.getId(), roomDTOList);
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