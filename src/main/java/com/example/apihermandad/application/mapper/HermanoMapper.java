package com.example.apihermandad.application.mapper;

import com.example.apihermandad.application.dto.HermanoCreateDto;
import com.example.apihermandad.application.dto.HermanoDto;
import com.example.apihermandad.domain.entity.Hermano;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface HermanoMapper {

    HermanoDto toDto(Hermano hermano);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activo",  constant = "true")
    Hermano toEntity(HermanoCreateDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Hermano partialUpdate(HermanoDto hermanoDto, @MappingTarget Hermano hermano);
}