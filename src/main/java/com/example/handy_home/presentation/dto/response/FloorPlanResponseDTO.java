package com.example.handy_home.presentation.dto.response;

import com.example.handy_home.common.utils.Status;

import java.util.List;
import java.util.Map;

public class FloorPlanResponseDTO extends ResponseDTO<List<Map<String, Object>>> {
    public FloorPlanResponseDTO(List<Map<String, Object>> body) {
        super(Status.S0000, true, body);
    }
}
