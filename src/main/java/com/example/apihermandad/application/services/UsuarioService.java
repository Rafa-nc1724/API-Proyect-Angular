package com.example.apihermandad.application.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.apihermandad.application.dto.UsuarioCreateDto;
import com.example.apihermandad.application.dto.UsuarioDto;
import com.example.apihermandad.application.mapper.UsuarioMapper;
import com.example.apihermandad.domain.entity.Usuario;
import com.example.apihermandad.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {
    private final UsuarioRepository userRepository;
    private final UsuarioMapper userMapper;


    UsuarioService(UsuarioRepository userRepository, UsuarioMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    //endpoint privado
    public List<UsuarioDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    //endpoint privado
    public UsuarioDto findById(Integer id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    //solo admin y junta
    public UsuarioDto create(UsuarioCreateDto dto) {
        Usuario user = userMapper.toCreateEntity(dto);
        return userMapper.toDto(userRepository.save(user));
    }

    // admin, junta y capataz
    public UsuarioDto update(Integer id, UsuarioDto dto) {
        Usuario user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        user.setName(dto.getName());
        user.setAddress(dto.getAddress());
        user.setPhone(dto.getPhone());

        return userMapper.toDto(userRepository.save(user));
    }

    //solo admin y junta
    public void invalidate(Integer id) {
        Usuario user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        user.setActive(false);
        userRepository.save(user);
    }


}
