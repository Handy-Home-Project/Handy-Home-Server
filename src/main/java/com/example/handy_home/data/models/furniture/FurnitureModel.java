package com.example.handy_home.data.models.furniture;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity(name = "FURNITURE")
@Table(name = "FURNITURE")
@NoArgsConstructor
public class FurnitureModel {

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

    @Column(name = "width", nullable = false)
    private Double width;

    @Column(name = "height", nullable = false)
    private Double height;

    @Column(name = "depth", nullable = false)
    private Double depth;

    public FurnitureModel(String name, Roundness roundness, Material material, Color color, Style style, Double width, Double height, Double depth) {
        this.name = name;
        this.roundness = roundness;
        this.material = material;
        this.color = color;
        this.style = style;
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Roundness getRoundness() {
        return roundness;
    }

    public Material getMaterial() {
        return material;
    }

    public Color getColor() {
        return color;
    }

    public Style getStyle() {
        return style;
    }


    public Double getWidth() {
        return width;
    }


    public Double getHeight() {
        return height;
    }


    public Double getDepth() {
        return depth;
    }
}
