package com.example.handy_home.presentation.controllers;

import com.example.handy_home.domain.entities.UserEntity;
import com.example.handy_home.domain.use_case.UserUseCase;
import com.example.handy_home.presentation.dto.response.CreateUserResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "001. User")
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserUseCase userUseCase;

    public UserController(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @PostMapping
    public ResponseEntity<CreateUserResponseDTO> createUser(@RequestParam("name") String name) {
        final UserEntity userEntity = userUseCase.registerUser(name);
        return ResponseEntity.ok(new CreateUserResponseDTO(userEntity));
    }

}
