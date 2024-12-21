package com.example.handy_home.common.utils;

public enum Status {
    S0000(200, null),
    E0000(401, null);

    Status(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public final int code;
    public final String message;
}
