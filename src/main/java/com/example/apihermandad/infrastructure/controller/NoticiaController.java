package com.example.apihermandad.infrastructure.controller;

import com.example.apihermandad.application.dto.NoticiaCreateUpdateDto;
import com.example.apihermandad.application.dto.NoticiaDto;
import com.example.apihermandad.application.services.NoticiaService;
import com.example.apihermandad.infrastructure.security.AllowedRoles;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
class NoticiaController {


    private final NoticiaService noticiaService;


    NoticiaController(NoticiaService noticiaService ) {
        this.noticiaService = noticiaService;

    }

    @GetMapping("/all")
    public ResponseEntity<List<NoticiaDto>> findAll() {
        return ResponseEntity.ok(noticiaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoticiaDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(noticiaService.findById(id));
    }

    @PostMapping
    @AllowedRoles
    public ResponseEntity<NoticiaDto> create(
            @RequestBody NoticiaCreateUpdateDto dto
    ) {
        return ResponseEntity.ok(noticiaService.create(dto));
    }


    @PutMapping("/{id}")
    @AllowedRoles
    public ResponseEntity<NoticiaDto> update(
            @PathVariable Integer id,
            @RequestBody NoticiaCreateUpdateDto dto
    ) {
        return ResponseEntity.ok(noticiaService.update(dto, id));
    }


    @DeleteMapping("/{id}")
    @AllowedRoles
    public ResponseEntity<NoticiaDto> delete(@PathVariable Integer id) {
        noticiaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
