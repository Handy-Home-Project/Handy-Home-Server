package com.example.handy_home.data.models;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity(name = "USERS")
@Table(name="USERS")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 5)
    private String name;

    public UserModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserModel() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

