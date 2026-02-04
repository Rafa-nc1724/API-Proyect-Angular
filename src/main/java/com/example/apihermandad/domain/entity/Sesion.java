package com.example.apihermandad.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sesiones")
public class Sesion {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Lob
    @Column(nullable = false)
    private String token;

    @Column(name = "fecha_emision", nullable = false)
    private Long fechaEmision;

    @Column(name = "fecha_expiracion", nullable = false)
    private Long fechaExpiracion;

    @Lob
    @Column(name = "finguer_print", nullable = false)
    private String fingerPrint;

    @Column(nullable = false)
    private Boolean active;
}