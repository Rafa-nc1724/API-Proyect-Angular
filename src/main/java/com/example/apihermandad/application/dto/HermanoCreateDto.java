package com.example.apihermandad.application.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO for {@link com.example.apihermandad.domain.entity.Hermano}
 */
@Setter
@Getter
public class HermanoCreateDto extends HermanoDto {
    private String password;

    public HermanoCreateDto(Long id, String nombre, String dni, String direccion, String telefono, String email,
            Boolean activo, String password) {
        super(id, nombre, dni, direccion, telefono, email, activo);
        this.password = password;
    }
}