package com.example.handy_home.presentation.dto.response;

import com.example.handy_home.common.utils.Status;

import java.util.List;

public class SearchAddressResponseDTO extends ResponseDTO<List<String>> {
    public SearchAddressResponseDTO(List<String> body) {
        super(Status.S0000, true, body);
    }
}
