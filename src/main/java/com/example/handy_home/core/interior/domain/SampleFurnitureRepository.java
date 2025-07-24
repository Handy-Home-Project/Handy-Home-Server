package com.example.handy_home.core.interior.domain;


import com.example.handy_home.core.interior.domain.enums.Color;
import com.example.handy_home.core.interior.domain.enums.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SampleFurnitureRepository extends JpaRepository<SampleFurniture, String> {
    List<SampleFurniture> findByStyleAndColorIn(Style style, List<Color> colors);
}
