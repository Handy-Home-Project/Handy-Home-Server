package com.example.handy_home.presentation.controllers;

import com.example.handy_home.common.dto.AnalyzeInteriorDTO;
import com.example.handy_home.core.home.application.dto.HomeDTO;
import com.example.handy_home.core.interior.application.SampleFurnitureService;
import com.example.handy_home.core.interior.application.dto.SampleFurnitureDto;
import com.example.handy_home.core.use_cases.ImageUseCase;
import com.example.handy_home.core.use_cases.InteriorUseCase;
import com.example.handy_home.presentation.response_dto.ResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "003. Interior")
@RestController
@RequestMapping("/api/interior")
@RequiredArgsConstructor
@Log4j2
public class InteriorController {

    private final InteriorUseCase interiorUseCase;
    private final SampleFurnitureService sampleFurnitureService;
    private final ImageUseCase imageUseCase;

    @PostMapping(value = "/ai_suggestion", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDTO<List<SampleFurnitureDto>>> getSearchSuggestions(@RequestPart("image") MultipartFile image, @RequestPart("home") String home) {
        String mimeType = image.getContentType();
        byte[] imageBytes = imageUseCase.resizeImageWithAspectRatio(image, 512);

        final AnalyzeInteriorDTO dto = interiorUseCase.getAnalyzeInteriorFromImageUrl(imageBytes, mimeType);
        List<SampleFurnitureDto> suggestionFurnitures = sampleFurnitureService.getSuggestionFurnitures(dto.style(), dto.colors());
        interiorUseCase.getSuggestionInterior(home, suggestionFurnitures);
        return ResponseEntity.ok(ResponseDTO.success(null));
    }
}
