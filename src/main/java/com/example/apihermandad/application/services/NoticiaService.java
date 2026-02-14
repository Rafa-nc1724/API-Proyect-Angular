package com.example.apihermandad.application.services;

import com.example.apihermandad.application.dto.NoticiaCreateUpdateDto;
import com.example.apihermandad.application.dto.NoticiaDto;
import com.example.apihermandad.application.mapper.NoticiaMapper;
import com.example.apihermandad.domain.entity.Image;
import com.example.apihermandad.domain.entity.Noticia;
import com.example.apihermandad.domain.repository.ImageRepository;
import com.example.apihermandad.domain.repository.NoticiaRepository;
import com.example.apihermandad.utils.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class NoticiaService {

    private final NoticiaRepository noticiaRepository;
    private final NoticiaMapper noticiaMapper;
    private final ImageService imageService;


    NoticiaService(NoticiaRepository noticiaRepository, NoticiaMapper noticiaMapper, ImageService imageService, ImageRepository imageRepository) {
        this.noticiaRepository = noticiaRepository;
        this.noticiaMapper = noticiaMapper;
        this.imageService = imageService;

    }

    public List<NoticiaDto> findAll() {
        return noticiaRepository.findAll()
                .stream()
                .map(noticiaMapper::toDto)
                .toList();
    }

    public NoticiaDto findById(Integer id) {
        Noticia noticia = noticiaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        HttpMessage.NOT_FOUND_NOTICE
                ));
        return noticiaMapper.toDto(noticia);
    }

    public NoticiaDto create(NoticiaCreateUpdateDto dto) {

        Noticia noticia = Noticia.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .build();

        if (dto.getImageId() != null) {
            Optional<Image> imageId = imageService.getImageById(dto.getImageId());
            imageId.ifPresent(noticia::setImage);
        }

        return noticiaMapper.toDto(noticiaRepository.save(noticia));
    }

    public NoticiaDto update(NoticiaCreateUpdateDto dto, Integer id) {

        Noticia noticia = noticiaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        HttpMessage.NOT_FOUND_NOTICE
                ));

        noticia.setTitle(dto.getTitle());
        noticia.setDescription(dto.getDescription());

        if (dto.getImageId() != null) {

            Image oldImage = noticia.getImage();

            Image newImage = imageService.getImageById(dto.getImageId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            HttpMessage.NOT_FOUND_IMG
                    ));

            noticia.setImage(newImage);

            noticiaRepository.save(noticia);

            if (oldImage != null) {
                imageService.deleteImage(oldImage);
            }
        }

        return noticiaMapper.toDto(noticia);
    }

    @Transactional
    public void delete(Integer id) {

        Noticia noticia = noticiaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        HttpMessage.NOT_FOUND_NOTICE
                ));

        Image image = noticia.getImage();

        if (image != null) {
            noticia.setImage(null);
            noticiaRepository.save(noticia);
        }

        if (image != null) {
            imageService.deleteImage(image);
        }

        noticiaRepository.delete(noticia);
    }
}
