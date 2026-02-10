package com.example.apihermandad.application.dto;

import lombok.*;
import java.time.LocalDate;

public record EventoCreateUpdateDto (
    String title,
    String description,
    LocalDate fecha,
    String groupName
){}
