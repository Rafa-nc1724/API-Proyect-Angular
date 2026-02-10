package com.example.apihermandad.application.dto;


import lombok.*;
import java.time.LocalDate;



@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Getter
@Setter
public class NoticiaCreateUpdateDto {
    private String title;
    private String description;
    private LocalDate fecha;
}
