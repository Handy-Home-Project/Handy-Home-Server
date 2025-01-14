package com.example.handy_home.presentation.response_dto;

import com.example.handy_home.common.utils.Status;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class ResponseDTO<T> {
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
}

