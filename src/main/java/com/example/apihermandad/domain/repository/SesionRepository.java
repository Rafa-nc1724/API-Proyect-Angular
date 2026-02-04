package com.example.apihermandad.domain.repository;

import com.example.apihermandad.domain.entity.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SesionRepository extends JpaRepository<Sesion, Integer> {

    @Query("""
        select s from Sesion s
        join fetch s.usuario u
        where s.token = :token
          and s.active = true
    """)
    Optional<Sesion> findActiveSessionWithUser(@Param("token") String token);
}