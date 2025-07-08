package com.example.handy_home.core.home.application;

import com.example.handy_home.core.home.application.dto.HomeDTO;

import java.io.File;

public interface HomeService {
    HomeDTO createHome(String userId, File image);
}
