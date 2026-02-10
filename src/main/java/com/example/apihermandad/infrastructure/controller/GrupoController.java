package com.example.apihermandad.infrastructure.controller;

import com.example.apihermandad.application.dto.GrupoCreateUpdateDto;
import com.example.apihermandad.application.dto.GrupoDto;
import com.example.apihermandad.application.services.GrupoService;
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
    public ResponseEntity<GrupoDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(grupoService.findById(id));
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GrupoDto> create(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) {
        GrupoCreateUpdateDto dto = new GrupoCreateUpdateDto();
        dto.setName(name);
        dto.setDescription(description);

        return ResponseEntity.ok(grupoService.create(dto, image));
    }


    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GrupoDto> update(
            @PathVariable Integer id,
            @RequestPart("data") GrupoCreateUpdateDto dto,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) {
        return ResponseEntity.ok(grupoService.update(id, dto, image));
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        grupoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}