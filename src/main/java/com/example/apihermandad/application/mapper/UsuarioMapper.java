package com.example.apihermandad.application.mapper;

import com.example.apihermandad.application.dto.UsuarioCreateDto;
import com.example.apihermandad.application.dto.UsuarioDto;
import com.example.apihermandad.domain.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDate;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = PasswordMapper.class, imports = LocalDate.class)
public interface UsuarioMapper {

    @Mapping(target = "password", source = "password", qualifiedByName = "encodePassword")
    @Mapping(target = "createDate", expression = "java(LocalDate.now())")
    @Mapping(target = "active", expression = "java(true)")
    @Mapping(
            target = "role",
            expression = "java(userDto.getRole() != null ? userDto.getRole() : \"usuario\")"
    )
    Usuario toCreateEntity(UsuarioCreateDto userDto);

    Usuario toUpdateEntity(UsuarioCreateDto userDto);

    UsuarioDto toDto(Usuario user);
}