package com.example.handy_home.infrastructure.data;

import com.example.handy_home.core.home.domain.HomeRepository;
import com.example.handy_home.infrastructure.data.jpa.HomeJpaRepository;
import com.example.handy_home.core.home.domain.Home;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class HomeRepositoryImpl implements HomeRepository {

    private final HomeJpaRepository jpaRepository;

    @Override
    public Home save(Home home) {
        return jpaRepository.save(home);
    }

}
