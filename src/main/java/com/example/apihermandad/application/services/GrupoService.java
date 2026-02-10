package com.example.apihermandad.application.services;

import com.example.apihermandad.application.dto.GrupoCreateUpdateDto;
import com.example.apihermandad.application.dto.GrupoDto;
import com.example.apihermandad.application.mapper.GrupoMapper;
import com.example.apihermandad.domain.entity.Grupo;
import com.example.apihermandad.domain.repository.GrupoRepository;
import com.example.apihermandad.utils.HttpMessage;
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

    public GrupoDto update(Integer id, GrupoCreateUpdateDto dto, MultipartFile image) {

        Grupo grupo = grupoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        HttpMessage.NOT_FOUND_GROUP
                ));

        grupo.setName(dto.getName());
        grupo.setDescription(dto.getDescription());

        if (image != null && !image.isEmpty()) {
            String imagePath = imageService.saveImage(image);
            imageService.deleteImageByPath(grupo.getImage());
            grupo.setImage(imagePath);
        }

        return grupoMapper.toDto(grupoRepository.save(grupo));
    }

    public void delete(Integer id) {
        if (!grupoRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    HttpMessage.NOT_FOUND_GROUP
            );
        }
        grupoRepository.deleteById(id);
    }
}