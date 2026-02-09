package com.example.apihermandad.application.mapper;

import java.io.IOException;
import java.time.LocalDate;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.example.apihermandad.application.dto.AssetCreateDto;
import com.example.apihermandad.application.dto.AssetDto;
import com.example.apihermandad.domain.entity.Asset;
import com.example.apihermandad.utils.CompressionTools;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = PasswordMapper.class)
public interface AssetMapper {
    @Mapping(target = "data", source = "data", qualifiedByName = "bytesToCompressedBase64")
    Asset toCreateEntity(AssetCreateDto dto);

    @Named("bytesToCompressedBase64")
    default String bytesToCompressedBase64(byte[] data) {
        if (data == null)
            return null;
        try {
            return CompressionTools.comprimirABase64(data);
        } catch (IOException e) {
            throw new RuntimeException("Error comprimiendo asset.data", e);
        }
    }

    @Mapping(target = "url", expression = "java(\"/api/asset/\" + asset.getId())")
    AssetDto toDto(Asset asset);
}