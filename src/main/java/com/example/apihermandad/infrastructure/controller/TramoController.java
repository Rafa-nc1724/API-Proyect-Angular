package com.example.apihermandad.infrastructure.controller;

import com.example.apihermandad.application.dto.TramoCreateUpdateDto;
import com.example.apihermandad.application.dto.TramoDto;
import com.example.apihermandad.application.services.TramoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tramos")
class TramoController {

    private final TramoService trService;


    TramoController(TramoService trService) {
        this.trService = trService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TramoDto>> getAll(){
        return ResponseEntity.ok(trService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TramoDto> getById(@PathVariable Integer id){
        return ResponseEntity.ok(trService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<TramoDto> create(@RequestBody TramoCreateUpdateDto dto){
        return ResponseEntity.ok(trService.create(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<TramoDto> update(@PathVariable Integer id, @RequestBody TramoCreateUpdateDto dto){
        return ResponseEntity.ok(trService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<TramoDto> delete(@PathVariable Integer id){
        trService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
