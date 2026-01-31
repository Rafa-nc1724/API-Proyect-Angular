package com.example.apihermandad.infrastructure.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.apihermandad.application.dto.HermanoCreateDto;
import com.example.apihermandad.application.dto.HermanoDto;
import com.example.apihermandad.application.services.HermanoService;

@Controller
@RequestMapping("/api/hermano")
@CrossOrigin(origins = "http://localhost:4200")
public class HermanoController {

    private final HermanoService hermanoService;

    public HermanoController(HermanoService hermanoService) {
        this.hermanoService = hermanoService;
    }

    /**
     * GET /api/hermano
     */
    @GetMapping("/all")
    public ResponseEntity<List<HermanoDto>> findAll() {
        return ResponseEntity.ok(hermanoService.findAll());
    }

    /**
     * POST /api/hermano
     */
    @PostMapping
    public ResponseEntity<HermanoDto> save(@RequestBody HermanoCreateDto dto) {
        HermanoDto created = hermanoService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * GET /api/hermano/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<HermanoDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(hermanoService.findById(id));
    }

    /**
     * PUT /api/hermano/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<HermanoDto> updatebyId(@PathVariable Long id, @RequestBody HermanoDto hermano) {
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
}
