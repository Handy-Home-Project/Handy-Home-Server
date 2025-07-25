package com.example.handy_home.infrastructure.data.jpa;

import com.example.handy_home.core.home.domain.Home;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HomeJpaRepository extends JpaRepository<Home, Long> {

    @Query("SELECT h FROM HOME h LEFT JOIN FETCH h.rooms WHERE h.user.id = :id")
    List<Home> findByUserId(@Param("id") String id);
}
