package com.example.apihermandad.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GrupoDto {
    private Integer id;
    private String name;
    private String description;
    private String image_id; // /image/{id}
}