package com.example.handy_home.core.interior.application;


import com.example.handy_home.core.interior.application.dto.SampleFurnitureDto;
import com.example.handy_home.core.interior.domain.SampleFurniture;
import com.example.handy_home.core.interior.domain.SampleFurnitureRepository;
import com.example.handy_home.core.interior.domain.enums.Color;
import com.example.handy_home.core.interior.domain.enums.Style;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class DefaultFurnitureService implements SampleFurnitureService {
    private final SampleFurnitureRepository sampleFurnitureRepository;

    @Override
    public List<SampleFurnitureDto> getSuggestionFurnitures(Style style, List<Color> colors) {
        List<SampleFurniture> list = sampleFurnitureRepository.findByStyleAndColorIn(style, colors);
        return list.stream()
                .map(furniture -> SampleFurnitureDto.builder()
                        .color(furniture.getColor())
                        .name(furniture.getName())
                        .fileName(furniture.getFileName())
                        .material(furniture.getMaterial())
                        .style(furniture.getStyle())
                .build()).collect(Collectors.toUnmodifiableList());
    }
}
