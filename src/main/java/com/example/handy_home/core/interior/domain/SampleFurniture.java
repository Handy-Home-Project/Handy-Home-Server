package com.example.handy_home.core.interior.domain;

import com.example.handy_home.core.interior.domain.enums.Color;
import com.example.handy_home.core.interior.domain.enums.Material;
import com.example.handy_home.core.interior.domain.enums.Style;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class SampleFurniture {

    @Id
    String fileName;

    String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "material")
    private Material material;

    @Enumerated(EnumType.STRING)
    @Column(name = "color")
    private Color color;

    @Enumerated(EnumType.STRING)
    @Column(name = "style")
    private Style style;

}
