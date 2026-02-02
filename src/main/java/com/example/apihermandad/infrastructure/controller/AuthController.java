package com.example.apihermandad.infrastructure.controller;

import com.example.apihermandad.application.dto.LoginRequestDto;
import com.example.apihermandad.application.dto.LoginResponseDto;
import com.example.apihermandad.application.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * POST api/auth/login
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @RequestBody LoginRequestDto request) {

        return ResponseEntity.ok(authService.login(request));
    }
}