package com.example.apihermandad.infrastructure.controller;

import com.example.apihermandad.application.dto.EventoDto;
import com.example.apihermandad.application.services.EventoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    /**
     * GET /api/eventos
     * Endpoint público
     */
    @GetMapping
    public List<EventoDto> getAllEventos() {
        return eventoService.getAllEventos();
    }
}