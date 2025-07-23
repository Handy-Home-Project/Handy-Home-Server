package com.example.handy_home.presentation.response_dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseDTO<T> {
    @JsonProperty(required = true)
    private final int statusCd;

    @JsonProperty(required = true)
    private final String statusMsg;

    @JsonProperty(value = "success", required = true)
    private final boolean success;

    @JsonProperty(value = "body")
    private final T body;

    public ResponseDTO(Status status, boolean success, T body) {
        this.statusCd = status.code;
        this.statusMsg = status.message;
        this.success = success;
        this.body = body;
    }

    public static <T> ResponseDTO<T> success(T body) {
        return new ResponseDTO<>(Status.S0000, true, body);
    }

    public static <T> ResponseDTO<T> fail(T body) {
        return new ResponseDTO<>(Status.E0000, false, body);
    }
}
