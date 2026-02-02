package com.example.apihermandad.application.dto;

import lombok.Data;
import java.io.Serializable;

/**
 * DTO for {@link com.example.apihermandad.domain.entity.Usuario}
 */
@Data
public class LoginRequestDto implements Serializable {
    String email;
    String password;
}