package com.example.handy_home.common.dto;

import java.util.List;

public record GeminiRequestDTO(List<Content> contents) {
    public record Content(List<Part> parts) {
        public record Part(
                String text,
                InlineData inlineData
        ) {
            public record InlineData(
                    String mimeType,
                    String data
            ) {}
        }
    }
}