package com.example.apihermandad.domain.repository;

import com.example.apihermandad.domain.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
}