package com.example.handy_home.domain.use_case;

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
}