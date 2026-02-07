package com.example.apihermandad.infrastructure.controller;

import com.example.apihermandad.application.dto.EventoDto;
import com.example.apihermandad.application.services.EventoService;
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

    /**
     * GET /api/events
     * Endpoint público
     */
    @GetMapping
    public List<EventoDto> getAllEventos() {
        return eventoService.getAllEventos();
    }

    /**
     * POST /api/events
     * Ejemplo: http://localhost:8080/api/events
     * Body:
     * {
     *   "titulo": "Ensayo general de la virgen",
     *   "descripcion": "Se realizará en la calle Trasmonjas",
     *   "fecha": "2026-02-12",
     *   "grupoId": 8
     * }
     * Headers:
     * Authorization-->Bearer "token del admin, junta o capataz"
     * EndPoint privado
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','JUNTA','CAPATAZ')")
    public ResponseEntity<EventoDto> create(@RequestBody EventoDto dto) {
        return ResponseEntity.ok(eventoService.create(dto));
    }

    /**
     * PUT /api/events
     * Ejemplo:http://localhost:8080/api/events/7
     * Body:{
     *   "titulo": "Ensayo general de la virgen",
     *   "descripcion": "Se realizará en la palaza de San Pedro",
     *   "fecha": "2026-02-12",
     *   "grupoId": 8
     * }
     * Headers:
     * Authorization-->Bearer "token del admin, junta o capataz"
     * Endpoint privadao
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','JUNTA','CAPATAZ')")
    public ResponseEntity<EventoDto> update(@PathVariable Integer id, @RequestBody EventoDto dto) {
        return ResponseEntity.ok(eventoService.update(id,dto));
    }

    /**
     * DELETE /api/events
     * Ejemplo:http://localhost:8080/api/events/7
     * Endpoint provado
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','JUNTA','CAPATAZ')")
    public ResponseEntity<EventoDto> delete(@PathVariable Integer id) {
        eventoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}