package com.example.handy_home.presentation.controllers;

import com.example.handy_home.domain.entities.UserEntity;
import com.example.handy_home.domain.use_cases.UserUseCase;
import com.example.handy_home.presentation.request_dto.CreateUserRequestDTO;
import com.example.handy_home.presentation.response_dto.CreateUserResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "001. User")
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserUseCase userUseCase;

    public UserController(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @PostMapping
        public ResponseEntity<CreateUserResponseDTO> createUser(@RequestBody CreateUserRequestDTO body) {
        final UserEntity userEntity = userUseCase.registerUser(body.name());
        return ResponseEntity.ok(new CreateUserResponseDTO(userEntity));
    }

}
