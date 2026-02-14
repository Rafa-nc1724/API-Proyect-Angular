package com.example.apihermandad.infrastructure.controller;

import com.example.apihermandad.application.dto.LoginRequestDto;
import com.example.apihermandad.application.dto.LoginResponseDto;
import com.example.apihermandad.application.dto.UsuarioDto;
import com.example.apihermandad.application.mapper.UsuarioMapper;
import com.example.apihermandad.application.services.AuthService;
import com.example.apihermandad.domain.entity.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UsuarioMapper usuarioMapper;

    public AuthController(AuthService authService, UsuarioMapper usuarioMapper) {
        this.authService = authService;
        this.usuarioMapper = usuarioMapper;
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @RequestBody LoginRequestDto request,
            HttpServletRequest httpRequest) {

        return ResponseEntity.ok(authService.login(request, httpRequest));
    }


    @GetMapping("/me")
    public UsuarioDto me(Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        return usuarioMapper.toDto(usuario);
    }
}