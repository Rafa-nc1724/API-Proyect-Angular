package com.example.apihermandad.application.services;

import com.example.apihermandad.application.dto.UsuarioCreateDto;
import com.example.apihermandad.application.dto.UsuarioDto;
import com.example.apihermandad.application.mapper.UsuarioMapper;
import com.example.apihermandad.domain.entity.Usuario;
import com.example.apihermandad.domain.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    // admin y junta
    @Transactional
    public UsuarioDto update(Integer id, UsuarioDto dto) {

        Usuario usuario = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Usuario no encontrado"
                        )
                );

        usuario.setName(dto.getName());
        usuario.setDni(dto.getDni());
        usuario.setAddress(dto.getAddress());
        usuario.setPhone(dto.getPhone());
        usuario.setEmail(dto.getEmail());
        usuario.setRole(dto.getRole());
        usuario.setActive(dto.getActive());

        return userMapper.toDto(userRepository.save(usuario));
    }

    //solo admin y junta
    public void invalidate(Integer id) {
        Usuario user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        user.setActive(false);
        userRepository.save(user);
    }


}
