package com.example.handy_home.common.dto;

import com.example.handy_home.data.models.furniture.Color;
import com.example.handy_home.data.models.furniture.Style;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public record AnalyzeInteriorDTO(Style style, List<Color> colors) {
}
