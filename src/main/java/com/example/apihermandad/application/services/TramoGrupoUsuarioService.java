package com.example.apihermandad.application.services;

import com.example.apihermandad.application.dto.UsuarioLiteDto;
import com.example.apihermandad.domain.entity.*;
import com.example.apihermandad.domain.repository.*;
import com.example.apihermandad.utils.HttpMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TramoGrupoUsuarioService {

    private final TramoGrupoUsuarioRepository repository;
    private final TramoRepository tramoRepository;
    private final GrupoRepository grupoRepository;
    private final UsuarioRepository usuarioRepository;

    /* ===============================
       ASIGNAR USUARIO A GRUPO/TRAMO
       =============================== */

    public void assignUsuarioToGrupoTramo(
            Integer tramoId,
            Integer grupoId,
            Integer usuarioId
    ) {

        // Regla: un usuario solo puede estar una vez en un grupo y tramo
        if (repository.existsByTramo_IdAndUsuario_Id(tramoId, usuarioId)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El usuario ya está asignado a un grupo en este tramo"
            );
        }

        Tramo tramo = tramoRepository.findById(tramoId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Tramo no encontrado"
                ));

        Grupo grupo = grupoRepository.findById(grupoId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        HttpMessage.NOT_FOUND_GROUP
                ));

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuario no encontrado"
                ));

        TramoGrupoUsuario tgu = new TramoGrupoUsuario();
        tgu.setTramo(tramo);
        tgu.setGrupo(grupo);
        tgu.setUsuario(usuario);

        repository.save(tgu);
    }

    /* ===============================
       USUARIOS DEL GRUPO Y TRAMO
       =============================== */

    public List<UsuarioLiteDto> getUsuariosByGrupoAndTramo(
            Integer grupoId,
            Integer tramoId
    ) {
        return repository.findUsuariosByGrupoAndTramo(grupoId, tramoId);
    }

    /* ===============================
       USUARIOS NO ASIGNADOS
       =============================== */

    public List<UsuarioLiteDto> getUsuariosNotInGrupoAndTramo(
            Integer grupoId,
            Integer tramoId
    ) {
        return repository.findUsuariosNotInGrupoAndTramo(grupoId, tramoId);
    }

    /* ===============================
       ELIMINAR ASIGNACIÓN
       =============================== */

    public void removeUsuarioFromGrupoTramo(
            Integer tramoId,
            Integer grupoId,
            Integer usuarioId
    ) {

        TramoGrupoUsuario tgu = repository
                .findByTramo_IdAndGrupo_IdAndUsuario_Id(
                        tramoId, grupoId, usuarioId
                )
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Asignación no encontrada"
                ));

        repository.delete(tgu);
    }
}