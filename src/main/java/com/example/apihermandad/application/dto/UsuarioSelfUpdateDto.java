package com.example.apihermandad.application.dto;


import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class UsuarioSelfUpdateDto {
    private String email;
    private String phone;
    private String address;

}
