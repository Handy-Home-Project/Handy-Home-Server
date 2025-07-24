package com.example.handy_home.core.use_cases.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FurniturePlacement {
    String fileName;
    String name;
    Position position;
    int rotation;

    @Getter
    @Setter
    @ToString
    public static class Position {
        int x;
        int y;
    }
}

