package com.example.apihermandad.application.dto;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO for {@link com.example.apihermandad.domain.entity.Hermano}
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
public class HermanoDto implements Serializable {
    private final Long id;
    private final String nombre;
    private final String dni;
    private final String direccion;
    private final String telefono;
    private final String email;
    private final Boolean activo;


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