package com.example.apihermandad.domain.repository;

import com.example.apihermandad.application.dto.UsuarioLiteDto;
import com.example.apihermandad.domain.entity.TramoGrupoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TramoGrupoUsuarioRepository
        extends JpaRepository<TramoGrupoUsuario, Integer> {

    boolean existsByTramo_IdAndUsuario_Id(
            Integer tramoId,
            Integer usuarioId
    );

    Optional<TramoGrupoUsuario> findByTramo_IdAndGrupo_IdAndUsuario_Id(
            Integer tramoId,
            Integer grupoId,
            Integer usuarioId
    );

    @Query("""
        select new com.example.apihermandad.application.dto.UsuarioLiteDto(
            u.id,
            u.name,
            u.email
        )
        from TramoGrupoUsuario tgu
        join tgu.usuario u
        where tgu.grupo.id = :grupoId
          and tgu.tramo.id = :tramoId
    """)
    List<UsuarioLiteDto> findUsuariosByGrupoAndTramo(
            @Param("grupoId") Integer grupoId,
            @Param("tramoId") Integer tramoId
    );

    @Query("""
        select new com.example.apihermandad.application.dto.UsuarioLiteDto(
            u.id,
            u.name,
            u.email
        )
        from Usuario u
        where u.id not in (
            select tgu.usuario.id
            from TramoGrupoUsuario tgu
            where tgu.grupo.id = :grupoId
              and tgu.tramo.id = :tramoId
        )
    """)
    List<UsuarioLiteDto> findUsuariosNotInGrupoAndTramo(
            @Param("grupoId") Integer grupoId,
            @Param("tramoId") Integer tramoId
    );
}