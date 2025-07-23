package com.example.handy_home.core.home.domain;

import com.example.handy_home.core.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "HOME")
@Table(name="HOME")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Home {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private User user;

    private boolean preview;

    @OneToMany(mappedBy = "home")
    private List<Room> rooms;

    public void addRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

}