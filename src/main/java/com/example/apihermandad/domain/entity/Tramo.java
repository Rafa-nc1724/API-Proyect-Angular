package com.example.apihermandad.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tramo")
public class Tramo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "salida", nullable = false)
    private LocalDate salida;

    @Column(name = "entrada", nullable = false)
    private LocalDate entrada;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

}