package com.example.apihermandad.infrastructure.controller;

import com.example.apihermandad.application.dto.UsuarioCreateDto;
import com.example.apihermandad.application.dto.UsuarioDto;
import com.example.apihermandad.application.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "http://localhost:4200")
class UsuarioController {

    private UsuarioService userService;

    public UsuarioController(UsuarioService userService) {
        this.userService = userService;
    }

    /**
     * GET /api/usuario/all
     */
    @GetMapping("/all")
    public ResponseEntity<List<UsuarioDto>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    /**
     *GET /api/usuario/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    /**
     * POST /api/usuario/register
     */
    @Transactional
    @PostMapping
    public ResponseEntity<UsuarioDto> register(@RequestBody UsuarioCreateDto userDto) {
        return ResponseEntity.ok(userService.create(userDto));
    }

}
