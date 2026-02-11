package com.example.apihermandad.infrastructure.controller;

import com.example.apihermandad.application.dto.ChangePasswordDto;
import com.example.apihermandad.application.dto.UsuarioCreateDto;
import com.example.apihermandad.application.dto.UsuarioDto;
import com.example.apihermandad.application.dto.UsuarioSelfUpdateDto;
import com.example.apihermandad.application.services.UsuarioService;
import com.example.apihermandad.domain.entity.Usuario;
import com.example.apihermandad.infrastructure.security.AllEditRoles;
import com.example.apihermandad.infrastructure.security.AllowedRoles;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "http://localhost:4200")
class UsuarioController {

    private final UsuarioService userService;

    public UsuarioController(UsuarioService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    @AllEditRoles
    public ResponseEntity<List<UsuarioDto>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }


    @GetMapping("/{id}")
    @AllowedRoles
    public ResponseEntity<UsuarioDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.findById(id));
    }


    @PostMapping
    @AllowedRoles
    public ResponseEntity<UsuarioDto> register(@RequestBody UsuarioCreateDto userDto) {
        return ResponseEntity.ok(userService.create(userDto));
    }


    @PutMapping("/{id}")
    @AllowedRoles
    public ResponseEntity<UsuarioDto> update(
            @PathVariable Integer id,
            @RequestBody UsuarioDto dto
    ) {
        return ResponseEntity.ok(userService.update(id, dto));
    }


    @PutMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UsuarioDto> updateMe(
            Authentication auth,
            @RequestBody UsuarioSelfUpdateDto dto
    ) {
        Usuario user = (Usuario) auth.getPrincipal();

        return ResponseEntity.ok(userService.updateSelf(user.getId(),dto));

    }


    @PutMapping("/me/password")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> updatePassword(
            Authentication auth,
            @RequestBody ChangePasswordDto dto
    ){
        Usuario user = (Usuario) auth.getPrincipal();
        userService.changePassword(user.getId(),dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @AllowedRoles
    public ResponseEntity<Void> invalidate(@PathVariable Integer id) {
        userService.invalidate(id);
        return ResponseEntity.noContent().build();
    }

}
