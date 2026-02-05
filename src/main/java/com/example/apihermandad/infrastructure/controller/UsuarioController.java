package com.example.apihermandad.infrastructure.controller;

import com.example.apihermandad.application.dto.UsuarioCreateDto;
import com.example.apihermandad.application.dto.UsuarioDto;
import com.example.apihermandad.application.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "http://localhost:4200")
class UsuarioController {

    private UsuarioService userService;

    public UsuarioController(UsuarioService userService) {
        this.userService = userService;
    }


    /*La lista la pueden ver los tras puesto que
    *el admin tiene todos los permisos
    * la junta puede ver la lista de usuarios
    * el capataz necesita la lista de usuarios para
    * hacer los cambios de usuario normal a costalero
    * */
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN','JUNTA', 'CAPATAZ')")
    public ResponseEntity<List<UsuarioDto>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    //ver un usuario concreto solo el admin y junta
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','JUNTA')")
    public ResponseEntity<UsuarioDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    //crear solo admin y junta
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','JUNTA')")
    public ResponseEntity<UsuarioDto> register(@RequestBody UsuarioCreateDto userDto) {
        return ResponseEntity.ok(userService.create(userDto));
    }

    //hacer el update los tres (admin / junta / capataz)
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','JUNTA','CAPATAZ')")
    public ResponseEntity<UsuarioDto> update(
            @PathVariable Integer id,
            @RequestBody UsuarioDto dto
    ) {
        return ResponseEntity.ok(userService.update(id, dto));
    }

    //invalidar usuario solo admin y junta
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','JUNTA')")
    public ResponseEntity<Void> invalidate(@PathVariable Integer id) {
        userService.invalidate(id);
        return ResponseEntity.noContent().build();
    }

}
