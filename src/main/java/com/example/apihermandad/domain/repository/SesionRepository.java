package com.example.apihermandad.domain.repository;

import com.example.apihermandad.domain.entity.Sesion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SesionRepository extends JpaRepository<Sesion, Integer> {
}