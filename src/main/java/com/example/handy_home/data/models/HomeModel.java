package com.example.handy_home.data.models;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity(name = "HOME")
@Table(name="HOME")
public class HomeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(optional = false)
    private UserModel user;

    @Column(name = "home_json", nullable = false, length = 32767)
    private String homeJson;

    public HomeModel(Long id, UserModel user, String homeJson) {
        this.id = id;
        this.user = user;
        this.homeJson = homeJson;
    }

    public HomeModel() {}

    public Long getId() {
        return id;
    }

    public UserModel getUser() {
        return user;
    }

    public String getHomeJson() {
        return homeJson;
    }
}