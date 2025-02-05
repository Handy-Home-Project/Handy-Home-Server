package com.example.handy_home.data.repositories;

import com.example.handy_home.data.data_sources.UserDataSource;
import com.example.handy_home.data.models.UserModel;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Repository
public class UserRepository {

    private final UserDataSource userDataSource;

    public UserRepository(UserDataSource userDataSource) {
        this.userDataSource = userDataSource;
    }

    public UserModel createUser(String name) {
        return userDataSource.save(new UserModel(null, name));
    }

    public UserModel getUser(Long userId) {
        final Optional<UserModel> user = userDataSource.findById(userId);
        return user.orElse(null);
    }


}
