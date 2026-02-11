package com.example.apihermandad.application.dto;

public record ChangePasswordDto (
    String oldPassword,
    String newPassword,
    String confirmPassword
    ){}
