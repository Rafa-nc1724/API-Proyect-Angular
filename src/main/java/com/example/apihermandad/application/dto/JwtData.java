package com.example.apihermandad.application.dto;


import lombok.*;


@Setter
@Data
@AllArgsConstructor
public class JwtData {
    private final String token;
    private final long issuedAt;
    private final long expiresAt;

}
