package com.example.handy_home.infrastructure;

import com.example.handy_home.core.home.domain.Room;
import com.example.handy_home.core.home.domain.RoomRepository;
import com.example.handy_home.infrastructure.data.jpa.RoomJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class RoomRepositoryImpl implements RoomRepository {
    private final RoomJpaRepository roomJpaRepository;
    @Override
    public List<Room> saveAll(List<Room> rooms) {
        return roomJpaRepository.saveAll(rooms);
    }
}
