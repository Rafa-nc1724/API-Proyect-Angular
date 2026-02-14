package com.example.apihermandad.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticiaDto implements Serializable {
    private Integer id;
    private String title;
    private String description;
    private Integer imageId;
}