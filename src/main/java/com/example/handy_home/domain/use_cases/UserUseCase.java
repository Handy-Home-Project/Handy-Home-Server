package com.example.handy_home.domain.use_cases;

import com.example.handy_home.data.models.UserModel;
import com.example.handy_home.data.repositories.UserRepository;
import com.example.handy_home.domain.entities.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserUseCase {

    private final UserRepository userRepository;

    public UserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity registerUser(String name) {
        final UserModel userModel = userRepository.createUser(name);
        return UserEntity.fromUserModel(userModel);
    }

    public UserEntity getUser(Long userId) {
        final UserModel userModel = userRepository.getUser(userId);
        return UserEntity.fromUserModel(userModel);
    }
}