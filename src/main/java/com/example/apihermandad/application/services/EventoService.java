package com.example.apihermandad.application.services;

import com.example.apihermandad.application.dto.EventoCreateUpdateDto;
import com.example.apihermandad.application.dto.EventoDto;
import com.example.apihermandad.application.mapper.EventoMapper;
import com.example.apihermandad.domain.entity.Evento;
import com.example.apihermandad.domain.entity.Grupo;
import com.example.apihermandad.domain.repository.EventoRepository;
import com.example.apihermandad.domain.repository.GrupoRepository;
import com.example.apihermandad.utils.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;
    private final EventoMapper eventoMapper;
    private final GrupoRepository grRepository;

    public EventoService(EventoRepository eventoRepository,
                         EventoMapper eventoMapper, GrupoRepository grRepository) {
        this.eventoRepository = eventoRepository;
        this.eventoMapper = eventoMapper;
        this.grRepository = grRepository;
    }

    //Este servicio es el único público
    public List<EventoDto> getAllEventos() {
        return eventoRepository.findAll()
                .stream()
                .map(eventoMapper::toDto)
                .toList();
    }


    public EventoDto create(EventoCreateUpdateDto dto){

        Grupo grup = grRepository.findByName(dto.groupName())
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        HttpMessage.NOT_FOUND_GROUP
                ));

        Evento event = Evento.builder()
                .title(dto.title())
                .description(dto.description())
                .fecha(dto.fecha())
                .id_group(grup)
                .build();

        return eventoMapper.toDto(eventoRepository.save(event));
    }

    public EventoDto update(Integer id, EventoCreateUpdateDto dto) {
        Evento event = eventoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        HttpMessage.NOT_FOUND_NOTICE
                ));

        Grupo grup = grRepository.findByName(dto.groupName())
                        .orElseThrow(()-> new ResponseStatusException(
                                HttpStatus.BAD_REQUEST,
                                HttpMessage.NOT_FOUND_GROUP
                        ));

        event.builder()
                .title(dto.title())
                .description(dto.description())
                .fecha(dto.fecha())
                .id_group(grup)
                .build();

        return eventoMapper.toDto(eventoRepository.save(event));
    }

    public void delete(Integer id) {
        if (!eventoRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    HttpMessage.NOT_FOUND_NOTICE
            );
        }
        eventoRepository.deleteById(id);
    }

    public List<EventoDto> getEventosByMonth(int year, int month) {

        if (month < 1 || month > 12) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El mes debe estar entre 1 y 12"
            );
        }

        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        return eventoRepository.findByFechaBetween(start, end)
                .stream()
                .map(eventoMapper::toDto)
                .toList();
    }
}