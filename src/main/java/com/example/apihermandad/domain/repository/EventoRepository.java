package com.example.apihermandad.domain.repository;

import com.example.apihermandad.domain.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Integer> {

    List<Evento> findByFechaBetween(LocalDate start, LocalDate end);

}