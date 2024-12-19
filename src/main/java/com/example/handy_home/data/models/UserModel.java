package com.example.handy_home.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserModel {
    @Id
    private Long id;

    private String name;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

