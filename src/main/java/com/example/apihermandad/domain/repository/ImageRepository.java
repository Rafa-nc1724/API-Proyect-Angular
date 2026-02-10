package com.example.apihermandad.domain.repository;

import com.example.apihermandad.domain.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    boolean existsByName(String name);
}