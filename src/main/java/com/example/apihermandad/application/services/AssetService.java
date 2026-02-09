package com.example.apihermandad.application.services;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.example.apihermandad.application.dto.AssetCreateDto;
import com.example.apihermandad.application.dto.AssetDto;
import com.example.apihermandad.application.mapper.AssetMapper;
import com.example.apihermandad.domain.repository.AssetRepository;
import com.example.apihermandad.utils.CompressionTools;

@Service
public class AssetService {
    private final AssetRepository assetRepository;
    private final AssetMapper assetMapper;

    AssetService(AssetRepository assetRepository, AssetMapper assetMapper) {
        this.assetRepository = assetRepository;
        this.assetMapper = assetMapper;
    }

    // endpoint privado
    public List<AssetDto> findAll() {
        return assetRepository.findAll()
                .stream()
                .map(assetMapper::toDto)
                .toList();
    }

    // endpoint privado
    public AssetDto findById(Integer id) throws Exception {
        return assetRepository.findById(id)
                .map(assetMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Asset no encontrado"));
    }

    public byte[] viewById(Integer id) throws Exception {
        return assetRepository.findById(id)
                .map(asset -> {
                    String data = asset.getData();

                    try {
                        return CompressionTools.descomprimirDeBase64(data);
                    } catch (IOException e) {
                        return null;
                    }
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Asset no encontrado"));
    }

    // solo admin y junta
    public AssetDto create(MultipartFile file) throws IOException {

        AssetCreateDto dto = AssetCreateDto.builder()
                .name(file.getOriginalFilename()) // Revisar esto
                .type(file.getContentType())
                .data(file.getBytes())
                .build();

        return assetMapper.toDto(assetRepository.save(assetMapper.toCreateEntity(dto)));
    }

}
