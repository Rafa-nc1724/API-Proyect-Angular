package com.example.apihermandad.application.mapper;

import com.example.apihermandad.application.dto.GrupoDto;
import com.example.apihermandad.domain.entity.Grupo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GrupoMapper {

    @Mapping(target = "imageUrl", expression = "java(\"/api/image/\" + grupo.getImage().getId())")
    GrupoDto toDto(Grupo grupo);

}