package com.example.handy_home.common.dto;

import java.util.Map;

public record FloorPlanDTO(String pyeongNo, String pyeongName, Double supplyArea, String floorPlanUrl) {

    public static FloorPlanDTO fromJson(Map<String, Object> json) {
        final String pyeongNo = (String) json.get("pyeongNo");
        final String pyeongName = (String) json.get("pyeongName");
        final double supplyArea = (Double) json.get("supplyAreaDouble");
        final String floorPlanUrl = (String) json.get("imageSrc");

        return new FloorPlanDTO(pyeongNo, pyeongName, supplyArea, floorPlanUrl);
    }
}