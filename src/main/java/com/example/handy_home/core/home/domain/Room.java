package com.example.handy_home.core.home.domain;

import com.example.handy_home.core.home.domain.emums.RoomType;
import com.example.handy_home.core.interior.domain.Furniture;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private RoomType type;
    private String vertexesJson;

    @ManyToOne
    private Home home;

    @OneToMany(mappedBy = "room")
    private List<Furniture> furnitures;
}
