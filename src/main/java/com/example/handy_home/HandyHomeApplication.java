package com.example.handy_home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class HandyHomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(HandyHomeApplication.class, args);
	}

}
