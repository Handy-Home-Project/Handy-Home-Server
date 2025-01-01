package com.example.handy_home.common.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record ComplexDetailDTO(ComplexDTO complex, List<FloorPlanDTO> floorPlans) {

    @SuppressWarnings("unchecked")
    public static ComplexDetailDTO fromJson(Map<String, Object> json) {
        final ComplexDTO complex = ComplexDTO.fromJson((Map<String, Object>) json.get("complexDetail"));
        final List<FloorPlanDTO> floorPlanList = new ArrayList<>();

        final List<Map<String, Object>> pyeongList = (List<Map<String, Object>>) json.get("complexPyeongDetailList");

        for (Map<String, Object> pyeong : pyeongList) {
            final List<Map<String, Object>> floorPlans = (List<Map<String, Object>>) pyeong.get("grandPlanList");

            if (floorPlans == null || floorPlans.isEmpty()) {
                continue;
            }

            final Map<String, Object> normalFloorPlan = floorPlans.getFirst();

            if (floorPlans.size() == 2) {
                normalFloorPlan.put("pyeongName", STR."\{pyeong.get("pyeongName")}² (기본형)");
                normalFloorPlan.put("pyeongNo", pyeong.get("pyeongNo"));
                normalFloorPlan.put("supplyAreaDouble", pyeong.get("supplyAreaDouble"));
                floorPlanList.add(FloorPlanDTO.fromJson(normalFloorPlan));

                final Map<String, Object> expandFloorPlan = floorPlans.getLast();
                expandFloorPlan.put("pyeongName", STR."\{pyeong.get("pyeongName")}² (확장형)");
                expandFloorPlan.put("pyeongNo", pyeong.get("pyeongNo"));
                expandFloorPlan.put("supplyAreaDouble", pyeong.get("supplyAreaDouble"));
                floorPlanList.add(FloorPlanDTO.fromJson(expandFloorPlan));
            } else {
                normalFloorPlan.put("pyeongName", STR."\{pyeong.get("pyeongName")}²");
                normalFloorPlan.put("pyeongNo", pyeong.get("pyeongNo"));
                normalFloorPlan.put("supplyAreaDouble", pyeong.get("supplyAreaDouble"));
                floorPlanList.add(FloorPlanDTO.fromJson(normalFloorPlan));
            }
        }

        return new ComplexDetailDTO(complex, floorPlanList);
    }
}
