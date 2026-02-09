package com.example.apihermandad.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apihermandad.domain.entity.Asset;

public interface AssetRepository extends JpaRepository<Asset, Integer> {

    Optional<Asset> findByName(String name);
}