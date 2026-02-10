package com.example.apihermandad.application.dto;


import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Getter
@Setter
public class TramoCreateUpdateDto {
    private LocalDate goOut;
    private LocalDate enter;
    private String description;
}
