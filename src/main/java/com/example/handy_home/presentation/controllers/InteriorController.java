package com.example.handy_home.presentation.controllers;

import com.example.handy_home.common.dto.AnalyzeInteriorDTO;
import com.example.handy_home.common.dto.ComplexDTO;
import com.example.handy_home.domain.use_cases.ImageUseCase;
import com.example.handy_home.domain.use_cases.InteriorUseCase;
import com.example.handy_home.presentation.response_dto.ReadSearchSuggestionsResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Tag(name = "003. Interior")
@RestController
@RequestMapping("/api/interior")
public class InteriorController {

    private final InteriorUseCase interiorUseCase;

    private final ImageUseCase imageUseCase;

    public InteriorController(InteriorUseCase interiorUseCase, ImageUseCase imageUseCase) {
        this.interiorUseCase = interiorUseCase;
        this.imageUseCase = imageUseCase;
    }

    @PostMapping(value = "/ai_suggestion", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ReadSearchSuggestionsResponseDTO> getSearchSuggestions(@RequestParam("image") MultipartFile image) {
        String mimeType = image.getContentType();
        byte[] imageBytes = imageUseCase.resizeImageWithAspectRatio(image, 512);

        final AnalyzeInteriorDTO dto = interiorUseCase.getAnalyzeInteriorFromImageUrl(imageBytes, mimeType);
        System.out.println(dto.toString());

        return ResponseEntity.ok(null);
    }

}
