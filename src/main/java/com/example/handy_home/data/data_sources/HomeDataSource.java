package com.example.handy_home.data.data_sources;

import com.example.handy_home.data.models.HomeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeDataSource extends JpaRepository<HomeModel, Long> {


}
