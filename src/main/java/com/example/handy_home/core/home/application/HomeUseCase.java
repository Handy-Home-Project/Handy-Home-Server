package com.example.handy_home.core.home.application;

import com.example.handy_home.core.home.application.dto.HomeDTO;
import com.example.handy_home.core.home.application.dto.RoomDTO;
import com.example.handy_home.core.home.domain.Home;
import com.example.handy_home.core.home.domain.HomeRepository;
import com.example.handy_home.core.home.domain.Room;
import com.example.handy_home.core.home.domain.emums.RoomType;
import com.example.handy_home.core.user.domain.User;
import com.example.handy_home.core.user.domain.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomeUseCase implements HomeService{

    private final HomeRepository homeRepository;
    private final UserRepository userRepository;
    private final HomeGenerator homeGenerator;

    @Override
    public HomeDTO createHome(String userId, File image) {
        try {
            User user = userRepository.getOrThrowById(userId);
            List<RoomDTO> rooms = homeGenerator.generate(image);
            Home home = homeRepository.save(Home.builder().user(user).name("Dummy").build());
            List<Room> saveRooms = rooms.stream().map(room -> Room.builder().name(room.name()).home(home).type(RoomType.valueOf(room.type())).vertexesJson(room.vertexes()).build()).collect(Collectors.toList());
            home.addRooms(saveRooms);
            return HomeDTO.fromEntity(home);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
