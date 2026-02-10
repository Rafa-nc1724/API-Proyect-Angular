package com.example.apihermandad.application.mapper;

import com.example.apihermandad.application.dto.EventoDto;
import com.example.apihermandad.domain.entity.Evento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EventoMapper {

    @Mapping(target = "grupoId", source = "idGrupo.id")
    @Mapping(target = "grupoNombre", source = "idGrupo.name")
    EventoDto toDto(Evento evento);
}