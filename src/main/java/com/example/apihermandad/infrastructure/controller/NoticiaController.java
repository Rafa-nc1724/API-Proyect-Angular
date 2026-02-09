package com.example.apihermandad.infrastructure.controller;

import com.example.apihermandad.application.dto.NoticiaDto;
import com.example.apihermandad.application.services.NoticiaService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NoticiaController {

    private final NoticiaService noticiaService;

    public NoticiaController(NoticiaService noticiaService) {
        this.noticiaService = noticiaService;
    }

    // 🔓 Público
    @GetMapping
    public List<NoticiaDto> getAllNoticias() {
        return noticiaService.getAllNoticias();
    }

    // Crear
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('ADMIN','JUNTA')")
    public NoticiaDto create(
            @RequestParam String titulo,
            @RequestParam String descripcion,
            @RequestParam LocalDate fecha,
            @RequestParam MultipartFile imagen
    ) {
        return noticiaService.create(titulo, descripcion, fecha, imagen);
    }

    // Delete
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','JUNTA')")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        noticiaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}