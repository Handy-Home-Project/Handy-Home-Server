package com.example.handy_home.presentation.dto.response;

import com.example.handy_home.common.utils.Status;

import java.util.List;
import java.util.Map;

public class SearchAddressResponseDTO extends ResponseDTO<List<Map<String, String>>> {
    public SearchAddressResponseDTO(List<Map<String, String>> body) {
        super(Status.S0000, true, body);
    }
}
