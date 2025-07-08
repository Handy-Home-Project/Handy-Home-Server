package com.example.handy_home.core.interior.domain;

import com.example.handy_home.core.home.domain.Room;
import com.example.handy_home.core.interior.domain.embedded.Location;
import com.example.handy_home.core.interior.domain.enums.Color;
import com.example.handy_home.core.interior.domain.enums.Material;
import com.example.handy_home.core.interior.domain.enums.Roundness;
import com.example.handy_home.core.interior.domain.enums.Style;
import jakarta.persistence.*;
import lombok.*;

/*
* 집에 배치되는 가구 정보
*
* */

@Entity(name = "FURNITURE")
@Table(name = "FURNITURE")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Furniture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "roundness", nullable = false)
    private Roundness roundness;

    @Enumerated(EnumType.STRING)
    @Column(name = "material", nullable = false)
    private Material material;

    @Enumerated(EnumType.STRING)
    @Column(name = "color", nullable = false)
    private Color color;

    @Enumerated(EnumType.STRING)
    @Column(name = "style", nullable = false)
    private Style style;

    @Embedded
    Location location;

    @ManyToOne
    @JoinColumn
    private Room room;
}
