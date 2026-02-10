package com.example.apihermandad.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;



@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tramo")
public class Tramo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "go_out", nullable = false)
    private LocalDate goOut;

    @NotNull
    @Column(name = "enter", nullable = false)
    private LocalDate enter;

    @NotNull
    @Column(name = "description", nullable = false, length = 100)
    private String description;

}