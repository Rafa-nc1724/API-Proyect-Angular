package com.example.apihermandad.application.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.example.apihermandad.domain.entity.Hermano}
 */
public class HermanoDto implements Serializable {
    private final Integer id;
    private final String nombre;
    private final String dni;
    private final String direccion;
    private final String telefono;
    private final String email;
    private final Boolean activo;

    public HermanoDto(Integer id, String nombre, String dni, String direccion, String telefono, String email, Boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.activo = activo;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getActivo() {
        return activo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HermanoDto entity = (HermanoDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.nombre, entity.nombre) &&
                Objects.equals(this.dni, entity.dni) &&
                Objects.equals(this.direccion, entity.direccion) &&
                Objects.equals(this.telefono, entity.telefono) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.activo, entity.activo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, dni, direccion, telefono, email, activo);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "nombre = " + nombre + ", " +
                "dni = " + dni + ", " +
                "direccion = " + direccion + ", " +
                "telefono = " + telefono + ", " +
                "email = " + email + ", " +
                "activo = " + activo + ")";
    }
}