package com.example.apihermandad.domain.repository;

import com.example.apihermandad.domain.entity.Tramo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TramoRepository extends JpaRepository<Tramo, Integer> {

    List<Tramo> findAllByIdNot(Integer id);

}