package com.example.apihermandad.application.services;

import com.example.apihermandad.application.dto.NoticiaDto;
import com.example.apihermandad.application.dto.NoticiaUpdateDto;
import com.example.apihermandad.application.mapper.NoticiaMapper;
import com.example.apihermandad.domain.entity.Image;
import com.example.apihermandad.domain.entity.Noticia;
import com.example.apihermandad.domain.repository.NoticiaRepository;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;


@Service
public class NoticiaService {

    private final NoticiaRepository noticiaRepository;
    private final NoticiaMapper noticiaMapper;
    private final ImageService imageService;

    public NoticiaService(NoticiaRepository noticiaRepository,
                          NoticiaMapper noticiaMapper,
                          ImageService imageService) {
        this.noticiaRepository = noticiaRepository;
        this.noticiaMapper = noticiaMapper;
        this.imageService = imageService;
    }

    //Único Servicio Público
    public List<NoticiaDto> getAllNoticias() {
        return noticiaRepository.findAll()
                .stream()
                .map(noticiaMapper::toDto)
                .toList();
    }

    //Crear
    public NoticiaDto create(String titulo,
                             String descripcion,
                             LocalDate fecha,
                             MultipartFile imagen) {

        Image savedImage = imageService.saveImage(imagen);

        Noticia noticia = new Noticia();
        noticia.setTitulo(titulo);
        noticia.setDescripcion(descripcion);
        noticia.setFecha(fecha);
        noticia.setImagenId(savedImage.getId());

        return noticiaMapper.toDto(noticiaRepository.save(noticia));
    }

    //Update
    @Transactional
    public NoticiaDto update(Integer id, NoticiaUpdateDto dto) {

        Noticia noticia = noticiaRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Noticia no encontrada"
                        )
                );

        noticia.setTitulo(dto.titulo());
        noticia.setDescripcion(dto.descripcion());
        noticia.setFecha(dto.fecha());

        if (dto.imagen() != null && !dto.imagen().isEmpty()) {
            Image image = imageService.saveImage(dto.imagen());
            noticia.setImagenId(image.getId());
        }

        return noticiaMapper.toDto(noticiaRepository.save(noticia));
    }

    //Delete
    public void delete(Integer id) {
        if (!noticiaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Noticia no encontrada");
        }
        noticiaRepository.deleteById(id);
    }
}