package com.example.handy_home.domain.use_cases;

import com.example.handy_home.data.models.HomeModel;
import com.example.handy_home.data.repositories.HomeRepository;
import com.example.handy_home.domain.entities.HomeEntity;
import com.example.handy_home.domain.entities.UserEntity;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class HomeUseCase {

    private final HomeRepository homeRepository;

    public HomeUseCase(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    public HomeEntity createHome(File image, UserEntity user) {
        final HomeModel homeModel = homeRepository.createHome(image, user.id());
        return HomeEntity.fromHomeModel(homeModel);
    }

}
