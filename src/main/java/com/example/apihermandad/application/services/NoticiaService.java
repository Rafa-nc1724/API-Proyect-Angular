package com.example.apihermandad.application.services;

import com.example.apihermandad.application.dto.NoticiaDto;
import com.example.apihermandad.application.mapper.NoticiaMapper;
import com.example.apihermandad.domain.entity.Noticia;
import com.example.apihermandad.domain.repository.NoticiaRepository;
import com.example.apihermandad.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticiaService {
    private final NoticiaRepository noticiaRepository;
    private final NoticiaMapper noticiaMapper;

    public NoticiaService(NoticiaRepository noticiaRepository, NoticiaMapper noticiaMapper) {
        this.noticiaRepository = noticiaRepository;
        this.noticiaMapper = noticiaMapper;
    }

    public List<NoticiaDto> getAllNoticias(){
        return noticiaRepository.findAll()
                .stream()
                .map(noticiaMapper::toDto)
                .toList();
    }
}
