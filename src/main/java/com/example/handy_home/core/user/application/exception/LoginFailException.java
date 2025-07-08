package com.example.handy_home.core.user.application.exception;

public class LoginFailException extends RuntimeException {
    public LoginFailException(String msg) {
        super(msg);
    }
}
