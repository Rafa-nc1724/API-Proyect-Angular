package com.example.apihermandad.domain.repository;

import com.example.apihermandad.domain.entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GrupoRepository extends JpaRepository<Grupo, Integer> {
    Optional<Grupo> findByName(String name);
}