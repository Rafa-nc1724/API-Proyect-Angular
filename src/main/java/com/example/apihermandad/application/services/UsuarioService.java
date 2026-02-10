package com.example.apihermandad.application.services;

import com.example.apihermandad.application.dto.UsuarioCreateDto;
import com.example.apihermandad.application.dto.UsuarioDto;
import com.example.apihermandad.application.dto.UsuarioSelfUpdateDto;
import com.example.apihermandad.application.mapper.UsuarioMapper;
import com.example.apihermandad.domain.entity.Usuario;
import com.example.apihermandad.domain.repository.UsuarioRepository;

import com.example.apihermandad.utils.HttpMessage;
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


    public List<UsuarioDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }


    public UsuarioDto findById(Integer id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }


    public UsuarioDto create(UsuarioCreateDto dto) {
        Usuario user = userMapper.toCreateEntity(dto);
        return userMapper.toDto(userRepository.save(user));
    }

    public UsuarioDto updateSelf(Integer id, UsuarioSelfUpdateDto dto){
        Usuario user=userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        HttpMessage.NOT_FOUND_USSER
                ));
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setAddress(dto.getAddress());

        return userMapper.toDto(userRepository.save(user));

    }


    @Transactional
    public UsuarioDto update(Integer id, UsuarioDto dto) {

        Usuario usuario = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                HttpMessage.NOT_FOUND_USSER
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


    public void invalidate(Integer id) {
        Usuario user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        user.setActive(false);
        userRepository.save(user);
    }


}
