package com.example.apihermandad.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String name;

    @Column(name = "dni", nullable = false, length = 100)
    private String dni;

    @Column(name = "direccion", nullable = false, length = 100)
    private String address;

    @Column(name = "telefono", length = 100)
    private String phone;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "pass", nullable = false, length = 100)
    private String password;

    @ColumnDefault("true")
    @Column(name = "activo", nullable = false)
    private Boolean active;

    @ColumnDefault("'usuario'")
    @Lob
    @Column(name = "rol", nullable = false)
    private String role;

    @Column(name = "fecha_ingreso", nullable = false)
    private LocalDate createDate;


}