package com.example.handy_home.presentation.request_dto;

import com.example.handy_home.core.home.application.dto.HomeDTO;
import com.example.handy_home.core.interior.application.dto.SampleFurnitureDto;

import java.util.List;

public record InteriorSuggestion(HomeDTO homeDTO,
        List<SampleFurnitureDto>  sampleFurnitures) {

}
