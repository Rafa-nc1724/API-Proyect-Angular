package com.example.apihermandad.application.services;

import com.example.apihermandad.application.dto.UsuarioCreateDto;
import com.example.apihermandad.application.dto.UsuarioDto;
import com.example.apihermandad.application.mapper.UsuarioMapper;
import com.example.apihermandad.domain.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository userRepository;
    private final UsuarioMapper userMapper;


    UsuarioService(UsuarioRepository userRepository, UsuarioMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UsuarioDto> findAll() {

        return userRepository
                .findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    public UsuarioDto findById(Integer id) {

        return userRepository
                .findById(id)
                .map(userMapper::toDto)
                .orElse(null);
    }

    public UsuarioDto create(UsuarioCreateDto userDto) {
        return userMapper
                .toDto(userRepository
                .save(userMapper
                .toCreateEntity(userDto)));
    }


}
