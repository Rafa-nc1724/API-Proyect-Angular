package com.example.apihermandad.application.dto;

import java.time.LocalDate;

public record EventoDto(
        Integer id,
        String title,
        String description,
        LocalDate fecha,
        Integer grupoId,
        String grupoNombre
) {
}