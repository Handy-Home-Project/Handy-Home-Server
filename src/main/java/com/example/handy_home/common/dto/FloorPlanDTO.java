package com.example.handy_home.common.dto;

import java.util.Map;

public record FloorPlanDTO(int pyeongNo, String pyeongName, double supplyArea, String floorPlanUrl) {

    public static FloorPlanDTO fromJson(Map<String, Object> json) {
        final int pyeongNo = (int) json.get("pyeongNo");
        final String pyeongName = (String) json.get("pyeongName");
        final double supplyArea = (double) json.get("supplyArea");
        final String floorPlanUrl = (String) json.get("floorPlanUrl");

        return new FloorPlanDTO(pyeongNo, pyeongName, supplyArea, floorPlanUrl);
    }
}