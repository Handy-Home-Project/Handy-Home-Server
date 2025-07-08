package com.example.handy_home.presentation.controllers;

import com.example.handy_home.core.user.application.UserService;
import com.example.handy_home.core.user.application.dto.UserDTO;
import com.example.handy_home.presentation.request_dto.JoinRequestDTO;
import com.example.handy_home.presentation.request_dto.LoginRequest;
import com.example.handy_home.presentation.response_dto.ResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "001. User")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    ResponseEntity<ResponseDTO<UserDTO>> login(@RequestBody LoginRequest loginRequest) {
        UserDTO loginUser = userService.login(loginRequest.id(), loginRequest.password());
        return ResponseEntity.ok(ResponseDTO.success(loginUser));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<UserDTO>> join(@RequestBody JoinRequestDTO body) {
        UserDTO joinUser = new UserDTO(body.id(), body.name(), body.password());
        userService.join(joinUser);
        return ResponseEntity.ok(ResponseDTO.success(joinUser));
    }

}
