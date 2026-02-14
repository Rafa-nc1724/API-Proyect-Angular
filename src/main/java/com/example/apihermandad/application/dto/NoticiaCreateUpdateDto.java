package com.example.apihermandad.application.dto;

import lombok.*;



@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Getter
@Setter
public class NoticiaCreateUpdateDto {
    private String title;
    private String description;
    private Integer imageId;
}
