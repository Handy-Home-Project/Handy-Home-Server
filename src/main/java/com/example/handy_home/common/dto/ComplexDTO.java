package com.example.handy_home.common.dto;

import java.util.Map;

public record ComplexDTO(String complexNo, String title, String address) {

    public static ComplexDTO fromJson(Map<String, Object> json) {
        final String complexNo = (String) json.get("complexNo");
        final String title = (String) json.get("complexName");
        String address = (String) json.get("cortarAddress");
        if (address == null) {
            address = (String) json.get("address");
        }

        return new ComplexDTO(complexNo, title, address);
    }
}
