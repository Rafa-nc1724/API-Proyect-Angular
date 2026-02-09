package com.example.apihermandad.application.mapper;

import com.example.apihermandad.application.dto.NoticiaDto;
import com.example.apihermandad.domain.entity.Noticia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface NoticiaMapper {

    @Mapping(
            target = "imagenUrl",
            expression = "java(noticia.getImagenId() != null ? \"api/images/\" + noticia.getImagenId() : null)"
    )
    NoticiaDto toDto(Noticia noticia);
}