package com.example.apihermandad.domain.repository;

import com.example.apihermandad.domain.entity.Hermano;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HermanoRepository extends JpaRepository<Hermano, Long> {
    Optional<Hermano> findById(Long id);
}