package com.example.handy_home.core.interior.application;

import com.example.handy_home.core.interior.application.dto.SampleFurnitureDto;
import com.example.handy_home.core.interior.domain.enums.Color;
import com.example.handy_home.core.interior.domain.enums.Style;

import java.util.List;

public interface SampleFurnitureService {
    List<SampleFurnitureDto> getSuggestionFurnitures(Style style, List<Color> colors);
}
