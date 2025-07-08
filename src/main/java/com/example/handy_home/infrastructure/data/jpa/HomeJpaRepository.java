package com.example.handy_home.infrastructure.data.jpa;

import com.example.handy_home.core.home.domain.Home;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeJpaRepository extends JpaRepository<Home, Long> {


}
