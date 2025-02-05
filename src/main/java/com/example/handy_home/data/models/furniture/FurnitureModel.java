package com.example.handy_home.data.models.furniture;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity(name = "FURNITURE")
@Table(name = "FURNITURE")
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

    @Column(name = "x", nullable = false)
    private Double x;

    @Column(name = "z", nullable = false)
    private Double z;

    @Column(name = "y", nullable = false)
    private Double y;

    public FurnitureModel(String name, Roundness roundness, Material material, Color color, Style style, Double x, Double z, Double y) {
        this.name = name;
        this.roundness = roundness;
        this.material = material;
        this.color = color;
        this.style = style;
        this.x = x;
        this.z = z;
        this.y = y;
    }

    public FurnitureModel() {}

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


    public Double getX() {
        return x;
    }


    public Double getZ() {
        return z;
    }


    public Double getY() {
        return y;
    }
}
