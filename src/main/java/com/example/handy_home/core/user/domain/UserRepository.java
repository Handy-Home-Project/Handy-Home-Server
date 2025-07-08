package com.example.handy_home.core.user.domain;

public interface UserRepository {
    User createUser(User user);
    User getOrThrowById(String id);
}
