package com.example.apihermandad.application.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Getter
@Setter
public class GrupoCreateUpdateDto {
    private String name;
    private String description;
    private Integer imageId;
}