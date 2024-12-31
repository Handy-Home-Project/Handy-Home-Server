package com.example.handy_home.presentation.controllers;

import com.example.handy_home.domain.use_cases.SeleniumUseCase;
import com.example.handy_home.domain.use_cases.SearchApiUseCase;
import com.example.handy_home.presentation.dto.response.SearchAddressResponseDTO;
import com.example.handy_home.presentation.dto.response.SearchResultDTO;
import com.example.handy_home.presentation.dto.response.SearchResultResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "002. Home")
@RestController
@RequestMapping("/api/home")
public class HomeController {

    private final SeleniumUseCase seleniumUseCase;
    private final SearchApiUseCase searchApiUseCase;

    public HomeController(SeleniumUseCase seleniumUseCase, SearchApiUseCase searchApiUseCase) {
        this.seleniumUseCase = seleniumUseCase;
        this.searchApiUseCase = searchApiUseCase;
    }

    @GetMapping("/search")
    public ResponseEntity<SearchAddressResponseDTO> searchAddressWithSelenium(@RequestParam("keyword") String keyword) {
        return ResponseEntity.ok(new SearchAddressResponseDTO(seleniumUseCase.searchKeywordInNaverRealty(keyword)));
    }

    @GetMapping("/search/api")
    public ResponseEntity<SearchResultResponseDTO> searchComplexesWithApi(@RequestParam("keyword") String keyword) {
        List<SearchResultDTO> results = searchApiUseCase.searchComplexes(keyword);
        return ResponseEntity.ok(new SearchResultResponseDTO(results));
    }

}
