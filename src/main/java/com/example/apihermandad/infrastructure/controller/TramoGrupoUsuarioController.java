package com.example.apihermandad.infrastructure.controller;

import com.example.apihermandad.application.dto.TramoGrupoUsuarioCreateDto;
import com.example.apihermandad.application.dto.UsuarioLiteDto;
import com.example.apihermandad.application.services.TramoGrupoUsuarioService;
import com.example.apihermandad.infrastructure.security.AllEditRoles;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tramo-grupo-usuario")
public class TramoGrupoUsuarioController {

    private final TramoGrupoUsuarioService service;

    public TramoGrupoUsuarioController(TramoGrupoUsuarioService service) {
        this.service = service;
    }


    @PostMapping
    @AllEditRoles
    public ResponseEntity<Void> assignUsuario(
            @RequestBody TramoGrupoUsuarioCreateDto dto
    ) {
        service.assignUsuarioToGrupoTramo(
                dto.tramoId(),
                dto.grupoId(),
                dto.usuarioId()
        );
        return ResponseEntity.ok().build();
    }


    @GetMapping("/usuarios")
    @AllEditRoles
    public ResponseEntity<List<UsuarioLiteDto>> getUsuariosByGrupoAndTramo(
            @RequestParam Integer grupoId,
            @RequestParam Integer tramoId
    ) {
        return ResponseEntity.ok(
                service.getUsuariosByGrupoAndTramo(grupoId, tramoId)
        );
    }


    @GetMapping("/usuarios-disponibles")
    @AllEditRoles
    public ResponseEntity<List<UsuarioLiteDto>> getUsuariosNotInGrupoAndTramo(
            @RequestParam Integer grupoId,
            @RequestParam Integer tramoId
    ) {
        return ResponseEntity.ok(
                service.getUsuariosNotInGrupoAndTramo(grupoId, tramoId)
        );
    }


    @DeleteMapping
    @AllEditRoles
    public ResponseEntity<Void> removeUsuario(
            @RequestParam Integer tramoId,
            @RequestParam Integer grupoId,
            @RequestParam Integer usuarioId
    ) {
        service.removeUsuarioFromGrupoTramo(tramoId, grupoId, usuarioId);
        return ResponseEntity.noContent().build();
    }
}