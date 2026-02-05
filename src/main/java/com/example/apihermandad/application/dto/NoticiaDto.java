package com.example.apihermandad.application.dto;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.example.apihermandad.domain.entity.Noticia}
 */
@Value
public class NoticiaDto implements Serializable {
    Integer id;
    String titulo;
    LocalDate fecha;
    String descripcion;
    String imagen;
}