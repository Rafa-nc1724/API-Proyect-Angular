package com.example.apihermandad.application.mapper;

import com.example.apihermandad.application.dto.NoticiaDto;
import com.example.apihermandad.domain.entity.Noticia;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface NoticiaMapper {

    @Mapping(target = "imageUrl", expression = "java(\"/api/image/\" + noticia.getImage().getId())")
    NoticiaDto toDto(Noticia noticia);

}