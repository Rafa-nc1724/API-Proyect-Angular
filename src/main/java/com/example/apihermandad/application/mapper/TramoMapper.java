package com.example.apihermandad.application.mapper;

import com.example.apihermandad.application.dto.TramoDto;
import com.example.apihermandad.domain.entity.Tramo;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TramoMapper {
    Tramo toEntity(TramoDto tramoDto);

    TramoDto toDto(Tramo tramo);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Tramo partialUpdate(TramoDto tramoDto, @MappingTarget Tramo tramo);
}