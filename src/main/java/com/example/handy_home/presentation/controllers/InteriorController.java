package com.example.handy_home.presentation.controllers;

import com.example.handy_home.common.dto.AnalyzeInteriorDTO;
import com.example.handy_home.common.dto.ComplexDTO;
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

    public InteriorController(InteriorUseCase interiorUseCase) {
        this.interiorUseCase = interiorUseCase;
    }

    @PostMapping(value = "/ai_suggestion", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ReadSearchSuggestionsResponseDTO> getSearchSuggestions(@RequestParam("image") MultipartFile image) throws IOException {
        String mimeType = image.getContentType();
        byte[] imageBytes = image.getBytes();

        final AnalyzeInteriorDTO dto = interiorUseCase.getAnalyzeInteriorFromImageUrl(imageBytes, mimeType);
        System.out.println(dto.style().toString());

        return ResponseEntity.ok(null);
    }

}
