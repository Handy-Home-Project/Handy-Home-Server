package com.example.handy_home;

import com.example.handy_home.data.models.furniture.Color;
import com.example.handy_home.domain.use_cases.InteriorUseCase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.stream.Stream;

@EnableScheduling
@SpringBootApplication
public class HandyHomeApplication {

    public static void main(String[] args) {
		SpringApplication.run(HandyHomeApplication.class, args);
	}

}
