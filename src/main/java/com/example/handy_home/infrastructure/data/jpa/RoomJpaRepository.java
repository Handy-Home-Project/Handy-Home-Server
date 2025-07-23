package com.example.handy_home.infrastructure.data.jpa;

import com.example.handy_home.core.home.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomJpaRepository extends JpaRepository<Room, Long> {
}
