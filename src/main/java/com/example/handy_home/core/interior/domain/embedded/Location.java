package com.example.handy_home.core.interior.domain.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Location {
    @Column(name = "x", nullable = false)
    private Double x;

    @Column(name = "z", nullable = false)
    private Double z;

    @Column(name = "y", nullable = false)
    private Double y;
}
