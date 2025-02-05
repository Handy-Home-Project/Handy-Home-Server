package com.example.handy_home.presentation.controllers;

import com.example.handy_home.common.dto.*;
import com.example.handy_home.data.models.UserModel;
import com.example.handy_home.domain.entities.HomeEntity;
import com.example.handy_home.domain.entities.UserEntity;
import com.example.handy_home.domain.use_cases.HomeUseCase;
import com.example.handy_home.domain.use_cases.ImageCacheUseCase;
import com.example.handy_home.domain.use_cases.SearchUseCase;
import com.example.handy_home.domain.use_cases.UserUseCase;
import com.example.handy_home.presentation.response_dto.HomeResponseDTO;
import com.example.handy_home.presentation.response_dto.ReadFloorPlansResponseDTO;
import com.example.handy_home.presentation.response_dto.ReadSearchSuggestionsResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Tag(name = "002. Home")
@RestController
@RequestMapping("/api/home")
public class HomeController {
    private final SearchUseCase searchUseCase;

    private final ImageCacheUseCase imageCacheUseCase;

    private final UserUseCase userUseCase;

    private final HomeUseCase homeUseCase;

    public HomeController(SearchUseCase searchUseCase, ImageCacheUseCase imageCacheUseCase, UserUseCase userUseCase, HomeUseCase homeUseCase) {
        this.searchUseCase = searchUseCase;
        this.imageCacheUseCase = imageCacheUseCase;
        this.userUseCase = userUseCase;
        this.homeUseCase = homeUseCase;
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

    @PostMapping("/create_home")
    public  ResponseEntity<HomeResponseDTO> createHome(@RequestParam("image_url") String imageUrl, @RequestParam("user_id") Long userId) {
        final File image = imageCacheUseCase.downloadImageToCache(imageUrl);
        final UserEntity user = userUseCase.getUser(userId);
        final HomeEntity home = homeUseCase.createHome(image, user);

        final HomeDetailDTO homeDetail = new HomeDetailDTO(HomeDTO.fromHomeEntity(home), UserDTO.fromUserEntity(user));

        return ResponseEntity.ok(new HomeResponseDTO(homeDetail));
    }

}
