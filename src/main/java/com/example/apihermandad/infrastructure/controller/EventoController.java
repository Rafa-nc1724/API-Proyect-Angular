package com.example.apihermandad.infrastructure.controller;

import com.example.apihermandad.application.dto.EventoDto;
import com.example.apihermandad.application.services.EventoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('ADMIN','JUNTA','CAPATAZ')")
    public ResponseEntity<EventoDto> create(@RequestBody EventoDto dto) {
        return ResponseEntity.ok(eventoService.create(dto));
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','JUNTA','CAPATAZ')")
    public ResponseEntity<EventoDto> update(@PathVariable Integer id, @RequestBody EventoDto dto) {
        return ResponseEntity.ok(eventoService.update(id,dto));
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','JUNTA','CAPATAZ')")
    public ResponseEntity<EventoDto> delete(@PathVariable Integer id) {
        eventoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}