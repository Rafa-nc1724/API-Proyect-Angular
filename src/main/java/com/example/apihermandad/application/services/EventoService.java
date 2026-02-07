package com.example.apihermandad.application.services;

import com.example.apihermandad.application.dto.EventoDto;
import com.example.apihermandad.application.mapper.EventoMapper;
import com.example.apihermandad.domain.entity.Evento;
import com.example.apihermandad.domain.entity.Grupo;
import com.example.apihermandad.domain.repository.EventoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;
    private final EventoMapper eventoMapper;

    public EventoService(EventoRepository eventoRepository,
                         EventoMapper eventoMapper) {
        this.eventoRepository = eventoRepository;
        this.eventoMapper = eventoMapper;
    }

    //Este servicio es el único público
    public List<EventoDto> getAllEventos() {
        return eventoRepository.findAll()
                .stream()
                .map(eventoMapper::toDto)
                .toList();
    }

    //Servicios privados solo para admin, junta y capataz.
    public EventoDto create(EventoDto dto){
        Evento event= new Evento();
        event.setTitulo(dto.titulo());
        event.setDescripcion(dto.descripcion());
        event.setFecha(dto.fecha());
        if (dto.grupoId() != null){
            Grupo grup = new Grupo();
            grup.setId(dto.grupoId());
            grup.setId(dto.grupoId());
            event.setIdGrupo(grup);
        }
        return eventoMapper.toDto(eventoRepository.save(event));
    }

    public EventoDto update(Integer id, EventoDto dto) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Evento no encontrado"
                ));

        evento.setTitulo(dto.titulo());
        evento.setDescripcion(dto.descripcion());
        evento.setFecha(dto.fecha());

        if (dto.grupoId() != null) {
            Grupo grupo = new Grupo();
            grupo.setId(dto.grupoId());
            evento.setIdGrupo(grupo);
        }

        return eventoMapper.toDto(eventoRepository.save(evento));
    }

    public void delete(Integer id) {
        if (!eventoRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Evento no encontrado"
            );
        }
        eventoRepository.deleteById(id);
    }
}