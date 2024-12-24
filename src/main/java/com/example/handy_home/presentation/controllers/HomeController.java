package com.example.handy_home.presentation.controllers;

import com.example.handy_home.domain.use_cases.SeleniumUseCase;
import com.example.handy_home.presentation.dto.response.SearchAddressResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "002. Home")
@RestController
@RequestMapping("/home")
public class HomeController {

    private final SeleniumUseCase seleniumUseCase;

    public HomeController(SeleniumUseCase seleniumUseCase) {
        this.seleniumUseCase = seleniumUseCase;
    }

    public ResponseEntity<SearchAddressResponseDTO> searchAddress(@RequestParam("keyword") String keyword) {

    }

}
