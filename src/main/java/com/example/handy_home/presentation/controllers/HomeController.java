package com.example.handy_home.presentation.controllers;

import com.example.handy_home.common.dto.ComplexDetailDTO;
import com.example.handy_home.domain.use_cases.SearchUseCase;
import com.example.handy_home.presentation.response_dto.ReadFloorPlansResponseDTO;
import com.example.handy_home.common.dto.ComplexDTO;
import com.example.handy_home.presentation.response_dto.ReadSearchSuggestionsResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "002. Home")
@RestController
@RequestMapping("/api/home")
public class HomeController {

    // private final SeleniumUseCase seleniumUseCase;
    private final SearchUseCase searchUseCase;

    // private final SeleniumWithDevToolsUseCase seleniumWithDevToolsUseCase;

    public HomeController(/*SeleniumUseCase seleniumUseCase, */SearchUseCase searchUseCase/*, SeleniumWithDevToolsUseCase seleniumWithDevToolsUseCase*/) {
        // this.seleniumUseCase = seleniumUseCase;
        this.searchUseCase = searchUseCase;
        // this.seleniumWithDevToolsUseCase = seleniumWithDevToolsUseCase;
    }

    @GetMapping("/search")
    public ResponseEntity<ReadSearchSuggestionsResponseDTO> getSearchSuggestions(@PathParam("keyword") String keyword) {
        List<ComplexDTO> results = searchUseCase.getSearchSuggestions(keyword);
        return ResponseEntity.ok(new ReadSearchSuggestionsResponseDTO(results));
    }

    @GetMapping("/floor_plan/{complex_no}")
    public ResponseEntity<ReadFloorPlansResponseDTO> getFloorPlans(@PathVariable("complex_no") String complexNo) {
        ComplexDetailDTO results = searchUseCase.getComplexDetails(complexNo);
        return ResponseEntity.ok(new ReadFloorPlansResponseDTO(results));
    }

}
