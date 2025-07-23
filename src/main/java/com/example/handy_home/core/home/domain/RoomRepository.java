package com.example.handy_home.core.home.domain;

import java.util.List;

public interface RoomRepository {
    List<Room> saveAll(List<Room> rooms);
}
