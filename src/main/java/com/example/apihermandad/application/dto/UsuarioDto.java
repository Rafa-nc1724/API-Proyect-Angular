package com.example.apihermandad.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.example.apihermandad.domain.entity.Usuario}
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioDto implements Serializable {
    Integer id;
    String nombre;
    String dni;
    String direccion;
    String telefono;
    String email;
    Byte activo;
    String rol;
    LocalDate fechaIngreso;
}