package com.example.apihermandad.application.services;

import com.example.apihermandad.application.dto.EventoDto;
import com.example.apihermandad.application.mapper.EventoMapper;
import com.example.apihermandad.domain.repository.EventoRepository;
import org.springframework.stereotype.Service;

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

    public List<EventoDto> getAllEventos() {
        return eventoRepository.findAll()
                .stream()
                .map(eventoMapper::toDto)
                .toList();
    }
}