package com.example.apihermandad.application.services;

import com.example.apihermandad.application.dto.HermanoCreateDto;
import com.example.apihermandad.application.dto.HermanoDto;
import com.example.apihermandad.application.mapper.HermanoMapper;
import com.example.apihermandad.domain.entity.Hermano;
import com.example.apihermandad.domain.repository.HermanoRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HermanoService {

    private final HermanoRepository hermanoRepository;
    private final HermanoMapper hermanoMapper;
    private final PasswordEncoder passwordEncoder;

    public HermanoService(HermanoRepository hermanoRepository, HermanoMapper hermanoMapper, PasswordEncoder passwordEncoder) {
        this.hermanoRepository = hermanoRepository;
        this.hermanoMapper = hermanoMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<HermanoDto> findAll() {
        // * Ejemplo de construccion con builder
        // HermanoDto hermano = HermanoDto.builder()
        // .activo(true)
        // .direccion("fulano")
        // .dni("23984324T")
        // .build();

        // return List.of(hermano);


        return hermanoRepository.findAll().stream().map(hermanoMapper::toDto).toList();
    }

    public HermanoDto findById(Long id) {
        return hermanoRepository.findById(id).map(hermanoMapper::toDto)
                .orElseThrow(()-> new RuntimeException("Hermano no encontrado"));
    }

    public HermanoDto create(HermanoCreateDto dto){
        Hermano hermano = hermanoMapper.toEntity(dto);
        hermano.setPassword(passwordEncoder.encode(dto.getPassword()));
        return hermanoMapper.toDto(hermanoRepository.save(hermano));
    }
}
