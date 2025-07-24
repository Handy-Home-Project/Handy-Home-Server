package com.example.handy_home.presentation.controllers;

import com.example.handy_home.core.home.application.HomeService;
import com.example.handy_home.core.home.application.dto.HomeDTO;
import com.example.handy_home.core.search.application.SearchUseCase;
import com.example.handy_home.core.search.application.dto.ComplexDTO;
import com.example.handy_home.core.search.application.dto.ComplexDetailDTO;
import com.example.handy_home.core.use_cases.ImageCacheUseCase;
import com.example.handy_home.presentation.response_dto.ReadFloorPlansResponseDTO;
import com.example.handy_home.presentation.response_dto.ReadSearchSuggestionsResponseDTO;
import com.example.handy_home.presentation.response_dto.ResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

@Tag(name = "002. Home")
@RestController
@RequestMapping("/api/home")
@RequiredArgsConstructor
public class HomeController {
    private final SearchUseCase searchUseCase;

    private final ImageCacheUseCase imageCacheUseCase;

    private final HomeService homeService;

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

    @PostMapping("/create_home")
    public ResponseEntity<ResponseDTO<HomeDTO>> createHome(@RequestParam("image_url") String imageUrl, @RequestParam("user_id") String userId) {
        final File image = imageCacheUseCase.downloadImageToCache(imageUrl);
        final HomeDTO home = homeService.createHome(userId, image);
        return ResponseEntity.ok(ResponseDTO.success(home));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<HomeDTO>>> getHomes(@RequestParam("userId") String userId) {
        return ResponseEntity.ok(ResponseDTO.success(homeService.getHomes(userId)));
    }

    @PostMapping("/{home_id}/preview")
    public ResponseEntity<ResponseDTO<HomeDTO>> previewHome(@PathVariable("home_id") Long homeId, @RequestParam("userId") String userId) {
        return ResponseEntity.ok(ResponseDTO.success(homeService.createHomePreview(userId, homeId)));
    }

}
