package com.example.apihermandad.infrastructure.controller;

import com.example.apihermandad.application.dto.EventoCreateUpdateDto;
import com.example.apihermandad.application.dto.EventoDto;
import com.example.apihermandad.application.services.EventoService;
import com.example.apihermandad.infrastructure.security.AllEditRoles;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }


    @GetMapping("/month")
    public ResponseEntity<List<EventoDto>> findByMonth(
            @RequestParam Integer year,
            @RequestParam Integer month
    ){
        return ResponseEntity.ok(eventoService.getEventosByMonth(year, month));
    }


    @GetMapping("/all")
    public List<EventoDto> getAllEventos() {
        return eventoService.getAllEventos();
    }


    @PostMapping
    @AllEditRoles
    public ResponseEntity<EventoDto> create(
            @RequestBody EventoCreateUpdateDto dto
    ) {
        return ResponseEntity.ok(eventoService.create(dto));
    }

    @PutMapping("/{id}")
    @AllEditRoles
    public ResponseEntity<EventoDto> update(
            @PathVariable Integer id,
            @RequestBody EventoCreateUpdateDto dto
    ) {
        return ResponseEntity.ok(eventoService.update(id, dto));
    }


    @DeleteMapping("/{id}")
    @AllEditRoles
    public ResponseEntity<EventoDto> delete(@PathVariable Integer id) {
        eventoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}