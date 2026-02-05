package com.example.apihermandad.domain.repository;

import com.example.apihermandad.domain.entity.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticiaRepository extends JpaRepository<Noticia, Integer> {
}