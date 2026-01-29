package com.example.apihermandad.infrastructure.controller;

import com.example.apihermandad.application.dto.HermanoCreateDto;
import com.example.apihermandad.application.dto.HermanoDto;
import com.example.apihermandad.application.mapper.HermanoMapper;
import com.example.apihermandad.application.services.HermanoService;
import com.example.apihermandad.domain.repository.HermanoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/hermanos")
@CrossOrigin(origins = "http://localhost:4200")
public class HermanoController {

    private final HermanoService hermanoService;

    public HermanoController(HermanoService hermanoService) {
        this.hermanoService = hermanoService;
    }

    /**
    * GET /api/hermanos
    */
    @GetMapping
    public ResponseEntity<List<HermanoDto>> findAll() {
        return ResponseEntity.ok(hermanoService.findAll());
    }

    /**
     * GET /api/hermanos/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<HermanoDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(hermanoService.findById(id));
    }

    /**
     * POST /api/hermanos
     */
    @PostMapping
    public ResponseEntity<HermanoDto> save(@RequestBody HermanoCreateDto dto) {
        HermanoDto created=hermanoService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

}
