package com.example.apihermandad.application.mapper;

import com.example.apihermandad.application.dto.NoticiaDto;
import com.example.apihermandad.domain.entity.Noticia;
import org.springframework.stereotype.Component;

@Component
public class NoticiaMapper {

    public NoticiaDto toDto(Noticia noticia) {

        return new NoticiaDto(
                noticia.getId(),
                noticia.getTitulo(),
                noticia.getFecha(),
                noticia.getDescripcion(),
                noticia.getImagen()
        );
    }
}