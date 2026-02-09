package com.example.apihermandad.application.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TramoDto implements Serializable {
    Integer id;
    LocalDate salida;
    LocalDate entrada;
    String descripcion;
}