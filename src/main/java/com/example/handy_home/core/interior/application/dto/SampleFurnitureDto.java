package com.example.handy_home.core.interior.application.dto;

import com.example.handy_home.core.interior.domain.enums.Color;
import com.example.handy_home.core.interior.domain.enums.Material;
import com.example.handy_home.core.interior.domain.enums.Style;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SampleFurnitureDto {
    private String fileName;
    private String name;
    private Color color;
    private Style style;
    private Material material;
}
