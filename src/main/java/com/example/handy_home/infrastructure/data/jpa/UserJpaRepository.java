package com.example.handy_home.infrastructure.data.jpa;

import com.example.handy_home.core.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, String> {
    Optional<User> findByName(String name);
}
