package com.example.handy_home.data.data_sources;

import com.example.handy_home.data.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataSource extends JpaRepository<UserModel, Long> {

}
