package com.example.apihermandad.infrastructure.controller;

import com.example.apihermandad.application.dto.NoticiaCreateUpdateDto;
import com.example.apihermandad.application.dto.NoticiaDto;
import com.example.apihermandad.application.services.NoticiaService;
import com.example.apihermandad.domain.repository.NoticiaRepository;
import com.example.apihermandad.infrastructure.security.AllowedRoles;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/news")
class NoticiaController {


    private final NoticiaService noticiaService;
    private final NoticiaRepository noticiaRepository;

    NoticiaController(NoticiaService noticiaService, NoticiaRepository noticiaRepository) {
        this.noticiaService = noticiaService;
        this.noticiaRepository = noticiaRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<NoticiaDto>> findAll() {
        return ResponseEntity.ok(noticiaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoticiaDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(noticiaService.findById(id));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @AllowedRoles
    public ResponseEntity<NoticiaDto> create(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) {
        NoticiaCreateUpdateDto dto = NoticiaCreateUpdateDto.builder()
                .title(title)
                .description(description)
                .fecha(fecha)
                .build();

        return ResponseEntity.ok(noticiaService.create(dto, image));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @AllowedRoles
    public ResponseEntity<NoticiaDto> update(
            @PathVariable Integer id,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) {
        NoticiaCreateUpdateDto dto = NoticiaCreateUpdateDto.builder()
                .title(title)
                .description(description)
                .fecha(fecha)
                .build();
        return ResponseEntity.ok(noticiaService.update(dto, id, image));
    }

    @DeleteMapping("/{id}")
    @AllowedRoles
    public ResponseEntity<NoticiaDto> delete(@PathVariable Integer id) {
        noticiaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
