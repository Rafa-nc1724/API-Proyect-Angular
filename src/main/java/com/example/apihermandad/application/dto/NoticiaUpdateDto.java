package com.example.apihermandad.application.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public record NoticiaUpdateDto(
        String titulo,
        String descripcion,
        LocalDate fecha,
        MultipartFile imagen // opcional
) {
}
