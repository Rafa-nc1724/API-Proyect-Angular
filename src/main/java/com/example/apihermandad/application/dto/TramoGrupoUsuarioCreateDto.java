package com.example.apihermandad.application.dto;

public record TramoGrupoUsuarioCreateDto(
        Integer tramoId,
        Integer grupoId,
        Integer usuarioId
) {}