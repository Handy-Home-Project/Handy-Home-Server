package com.example.handy_home.core.home.domain;

import java.util.List;

public interface HomeRepository {
    Home save(Home saveHome);
    List<Home> findHomesByUser(String userId);
}
