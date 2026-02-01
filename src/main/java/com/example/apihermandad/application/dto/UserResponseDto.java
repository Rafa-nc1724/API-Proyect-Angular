package com.example.apihermandad.application.dto;


import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class UserResponseDto {
    private String token;
    private UsuarioDto user;
}
