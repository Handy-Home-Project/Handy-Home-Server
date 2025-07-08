package com.example.handy_home.common.dto;

import com.example.handy_home.core.interior.domain.enums.Color;
import com.example.handy_home.core.interior.domain.enums.Style;

import java.util.List;

public record AnalyzeInteriorDTO(Style style, List<Color> colors) {

    @Override
    public String toString() {
        return "AnalyzeInteriorDTO(style: "+style.toString()+", colors: "+colors.stream().map(color -> color.toString()).toList()+ ")";
    }
}