package com.example.handy_home.infrastructure.data;

import com.example.handy_home.core.user.domain.User;
import com.example.handy_home.core.user.domain.UserRepository;
import com.example.handy_home.infrastructure.data.jpa.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository jpaRepository;

    @Override
    public User createUser(User user) {
        return jpaRepository.save(user);
    }

    @Override
    public User getOrThrowById(String id) {
        Optional<User> user = jpaRepository.findById(id);
        return user.orElseThrow();
    }
}
