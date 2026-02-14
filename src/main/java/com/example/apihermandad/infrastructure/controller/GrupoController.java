package com.example.apihermandad.infrastructure.controller;

import com.example.apihermandad.application.dto.GrupoCreateUpdateDto;
import com.example.apihermandad.application.dto.GrupoDto;
import com.example.apihermandad.application.services.GrupoService;
import com.example.apihermandad.infrastructure.security.AllEditRoles;
import com.example.apihermandad.infrastructure.security.AllowedRoles;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/grupos")
public class GrupoController {

    private final GrupoService grupoService;

    public GrupoController(GrupoService grupoService) {
        this.grupoService = grupoService;
    }


    @GetMapping
    public ResponseEntity<List<GrupoDto>> findAll() {
        return ResponseEntity.ok(grupoService.findAll());
    }


    @GetMapping("/{id}")
    @AllEditRoles
    public ResponseEntity<GrupoDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(grupoService.findById(id));
    }


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GrupoDto> create(
            @RequestBody GrupoCreateUpdateDto dto
    ) {
        return ResponseEntity.ok(grupoService.create(dto));
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GrupoDto> update(
            @PathVariable Integer id,
            @RequestBody GrupoCreateUpdateDto dto
    ) {
        return ResponseEntity.ok(grupoService.update(id, dto));
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        grupoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}