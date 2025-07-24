package com.example.handy_home.core.home.application;

import com.example.handy_home.core.home.application.dto.HomeDTO;
import com.example.handy_home.core.home.application.dto.RoomDTO;
import com.example.handy_home.core.home.domain.Home;
import com.example.handy_home.core.home.domain.HomeRepository;
import com.example.handy_home.core.home.domain.Room;
import com.example.handy_home.core.home.domain.RoomRepository;
import com.example.handy_home.core.home.domain.emums.RoomType;
import com.example.handy_home.core.user.application.dto.UserDTO;
import com.example.handy_home.core.user.domain.User;
import com.example.handy_home.core.user.domain.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomeUseCase implements HomeService {

    private final HomeRepository homeRepository;
    private final UserRepository userRepository;
    private final HomeGenerator homeGenerator;
    private final RoomRepository roomRepository;

    @Override
    public HomeDTO createHome(String userId, File image) {
        try {
            User user = userRepository.getOrThrowById(userId);
            JsonNode roomJson = homeGenerator.generate(image);
            Home home = homeRepository.save(Home.builder().user(user).name("내 집").build());
            List<Room> rooms = new ArrayList<>();
            for(JsonNode room : roomJson) {
                String name = room.get("name").asText();
                String type = room.get("type").asText();
                String vertexes = room.get("vertex").toString();
                rooms.add(
                    Room.builder()
                        .name(name)
                        .home(home)
                        .type(RoomType.valueOf(type))
                        .vertexesJson(vertexes)
                        .build()
                );
            }

            List<Room> saveRooms = roomRepository.saveAll(rooms);
            home.addRooms(saveRooms);
            return HomeDTO.fromEntity(home);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public HomeDTO createHomePreview(String userId, Long homeId) {
        try {
            Home home = homeRepository.findHomeById(homeId);
            if (!home.getUser().getId().equals(userId)) throw new Exception();

            Home homePreviewBuilder = Home.builder()
                    .sourceId(home.getId())
                    .name(home.getName())
                    .user(home.getUser())
                    .preview(true)
                    .build();

            Home homePreview = homeRepository.save(homePreviewBuilder);
            Home returnHome = Home.builder()
                    .sourceId(home.getId())
                    .name(home.getName())
                    .rooms(home.getRooms())
                    .user(home.getUser())
                    .preview(true)
                    .build();
            return HomeDTO.fromEntity(homePreview);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<HomeDTO> getHomes(String userId) {
        List<Home> homes = homeRepository.findHomesByUser(userId);
        Optional<Home> sourceHome = homes.stream().filter(home -> !home.isPreview()).findFirst();
        return sourceHome.map(value -> homes.stream().map(home -> new HomeDTO(home.getId(), home.getName(), value.getRooms().stream().map(RoomDTO::fromEntity).toList(), UserDTO.fromEntity(home.getUser()), home.isPreview(), home.getSourceId())).collect(Collectors.toUnmodifiableList())).orElseGet(ArrayList::new);
    }

}
