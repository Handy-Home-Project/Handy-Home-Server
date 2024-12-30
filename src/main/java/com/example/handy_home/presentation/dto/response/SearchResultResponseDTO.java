package com.example.handy_home.presentation.dto.response;

import com.example.handy_home.common.utils.Status;

import java.util.List;

public class SearchResultResponseDTO extends ResponseDTO<List<SearchResultDTO>> {

    public SearchResultResponseDTO(List<SearchResultDTO> body) {
        super(Status.S0000, true, body);
    }
}
