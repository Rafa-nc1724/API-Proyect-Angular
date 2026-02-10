package com.example.apihermandad.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TramoDto implements Serializable {
    Integer id;
    @NotNull
    LocalDate goOut;
    @NotNull
    LocalDate enter;
    @NotNull
    String description;
}