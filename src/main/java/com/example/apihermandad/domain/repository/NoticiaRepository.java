package com.example.apihermandad.domain.repository;

import com.example.apihermandad.domain.entity.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface NoticiaRepository extends JpaRepository<Noticia, Integer> {
    Optional<Noticia> findById(Integer id);
}