package com.example.apihermandad.application.mapper;

import com.example.apihermandad.application.dto.EventoDto;
import com.example.apihermandad.domain.entity.Evento;
import org.springframework.stereotype.Component;

@Component
public class EventoMapper {

    public EventoDto toDto(Evento evento) {

        return new EventoDto(
                evento.getId(),
                evento.getTitulo(),
                evento.getDescripcion(),
                evento.getFecha(),
                evento.getIdGrupo() != null ? evento.getIdGrupo().getId() : null,
                evento.getIdGrupo() != null ? evento.getIdGrupo().getNombre() : null
        );
    }
}