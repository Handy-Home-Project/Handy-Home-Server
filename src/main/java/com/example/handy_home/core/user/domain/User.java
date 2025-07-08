package com.example.handy_home.core.user.domain;

import com.example.handy_home.core.home.domain.Home;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {
    @Id
    @Column
    private String id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    String password;

    @OneToMany(mappedBy = "user")
    private List<Home> homes;

    public void validUserJoinOrThrow() {
        if(this.id == null || this.id.length() <= 5) {
            throw new IllegalArgumentException("Invalid user Id");
        }

        if(this.password == null || this.password.length() <= 5 ) {
            throw new IllegalArgumentException("Not Enough user Password");
        }

//      this.name.matches("^[a-zA-Z가-힣]*$")
        if(this.name == null || this.name.isBlank() ) {
            throw new IllegalArgumentException("Invalid user Name");
        }
    }

    public void checkPasswordOrThrow(String password) {
        if(!this.password.equals(password)) {
            throw new IllegalArgumentException("Password가 일치하지 않습니다.");
        }
    }
}
