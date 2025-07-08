package com.example.handy_home.core.home.domain.emums;

public enum RoomType {
    LIVING_ROOM("거실"),
    BED_ROOM("침실"),
    KITCHEN("주방"),
    BATH_ROOM("욕실"),
    STUDY("서재"),
    STORAGE("창고"),
    HALLWAY("복도"),
    ENTRANCE("현관"),
    COMMON_AREA("공용공간");

    private final String displayName;

    RoomType(String displayName) {
        this.displayName = displayName;
    }



}
