package com.example.apihermandad.application.services;

import com.example.apihermandad.application.dto.GrupoCreateUpdateDto;
import com.example.apihermandad.application.dto.GrupoDto;
import com.example.apihermandad.application.mapper.GrupoMapper;
import com.example.apihermandad.domain.entity.Grupo;
import com.example.apihermandad.domain.entity.Image;
import com.example.apihermandad.domain.repository.GrupoRepository;
import com.example.apihermandad.utils.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    public List<GrupoDto> findAll() {
        return grupoRepository.findAll()
                .stream()
                .map(grupoMapper::toDto)
                .toList();
    }

    public GrupoDto findById(Integer id) {
        Grupo grupo = grupoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        HttpMessage.NOT_FOUND_GROUP
                ));
        return grupoMapper.toDto(grupo);
    }

    public GrupoDto create(GrupoCreateUpdateDto dto) {

        Grupo grupo = new Grupo();
        grupo.setName(dto.getName());
        grupo.setDescription(dto.getDescription());

        if (dto.getImageId() != null) {
            Image image = imageService.getImageById(dto.getImageId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            HttpMessage.NOT_FOUND_IMG
                    ));
            grupo.setImage(image);
        }

        return grupoMapper.toDto(grupoRepository.save(grupo));
    }

    @Transactional
    public GrupoDto update(Integer id, GrupoCreateUpdateDto dto) {

        Grupo grupo = grupoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        HttpMessage.NOT_FOUND_GROUP
                ));

        grupo.setName(dto.getName());
        grupo.setDescription(dto.getDescription());

        // Cambio de imagen
        if (dto.getImageId() != null) {

            // 1️⃣ Desvincular imagen antigua
            Image oldImage = grupo.getImage();
            grupo.setImage(null);
            grupoRepository.save(grupo);

            // 2️⃣ Eliminar imagen antigua
            if (oldImage != null) {
                imageService.deleteImage(oldImage);
            }

            // 3️⃣ Vincular nueva imagen
            Image newImage = imageService.getImageById(dto.getImageId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            HttpMessage.NOT_FOUND_IMG
                    ));
            grupo.setImage(newImage);
        }

        return grupoMapper.toDto(grupoRepository.save(grupo));
    }

    @Transactional
    public void delete(Integer id) {

        Grupo grupo = grupoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        HttpMessage.NOT_FOUND_GROUP
                ));

        Image image = grupo.getImage();

        grupo.setImage(null);
        grupoRepository.save(grupo);

        if (image != null) {
            imageService.deleteImage(image);
        }

        grupoRepository.delete(grupo);
    }
}