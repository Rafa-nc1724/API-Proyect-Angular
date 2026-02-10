package com.example.apihermandad.application.services;

import com.example.apihermandad.application.dto.GrupoCreateUpdateDto;
import com.example.apihermandad.application.dto.GrupoDto;
import com.example.apihermandad.application.mapper.GrupoMapper;
import com.example.apihermandad.domain.entity.Grupo;
import com.example.apihermandad.domain.repository.GrupoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class GrupoService {

    private final GrupoRepository grupoRepository;
    private final GrupoMapper grupoMapper;
    private final ImageService imageService;

    public GrupoService(
            GrupoRepository grupoRepository,
            GrupoMapper grupoMapper,
            ImageService imageService
    ) {
        this.grupoRepository = grupoRepository;
        this.grupoMapper = grupoMapper;
        this.imageService = imageService;
    }

    // 🔐 Autenticado
    public List<GrupoDto> findAll() {
        return grupoRepository.findAll()
                .stream()
                .map(grupoMapper::toDto)
                .toList();
    }

    // 🔐 Autenticado
    public GrupoDto findById(Integer id) {
        Grupo grupo = grupoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Grupo no encontrado"
                ));
        return grupoMapper.toDto(grupo);
    }

    // 🔐 ADMIN
    public GrupoDto create(GrupoCreateUpdateDto dto, MultipartFile image) {

        Grupo grupo = new Grupo();
        grupo.setName(dto.getName());
        grupo.setDescription(dto.getDescription());

        if (image != null && !image.isEmpty()) {
            String imagePath = imageService.saveImage(image);
            grupo.setImage(imagePath);
        }

        return grupoMapper.toDto(grupoRepository.save(grupo));
    }

    // 🔐 ADMIN
    public GrupoDto update(Integer id, GrupoCreateUpdateDto dto, MultipartFile image) {

        Grupo grupo = grupoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Grupo no encontrado"
                ));

        grupo.setName(dto.getName());
        grupo.setDescription(dto.getDescription());

        if (image != null && !image.isEmpty()) {
            String imagePath = imageService.saveImage(image);
            grupo.setImage(imagePath);
        }

        return grupoMapper.toDto(grupoRepository.save(grupo));
    }

    // 🔐 ADMIN
    public void delete(Integer id) {
        if (!grupoRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Grupo no encontrado"
            );
        }
        grupoRepository.deleteById(id);
    }
}