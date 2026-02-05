package com.example.apihermandad.application.dto;

import java.time.LocalDate;

public record EventoDto(
        Integer id,
        String titulo,
        String descripcion,
        LocalDate fecha,
        Integer grupoId,
        String grupoNombre
) {
}