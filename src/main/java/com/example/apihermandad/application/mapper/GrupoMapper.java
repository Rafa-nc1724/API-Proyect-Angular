package com.example.apihermandad.application.mapper;

import com.example.apihermandad.application.dto.GrupoDto;
import com.example.apihermandad.domain.entity.Grupo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GrupoMapper {

    GrupoDto toDto(Grupo grupo);
}