package com.example.apihermandad.application.services;

import com.example.apihermandad.application.dto.NoticiaCreateUpdateDto;
import com.example.apihermandad.application.dto.NoticiaDto;
import com.example.apihermandad.application.mapper.NoticiaMapper;
import com.example.apihermandad.domain.entity.Noticia;
import com.example.apihermandad.domain.repository.NoticiaRepository;
import com.example.apihermandad.utils.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class NoticiaService {

    private final NoticiaRepository noticiaRepository;
    private final NoticiaMapper noticiaMapper;
    private final ImageService imageService;

    NoticiaService(NoticiaRepository noticiaRepository, NoticiaMapper noticiaMapper, ImageService imageService) {
        this.noticiaRepository = noticiaRepository;
        this.noticiaMapper = noticiaMapper;
        this.imageService = imageService;
    }

    public List<NoticiaDto> findAll(){
         return noticiaRepository.findAll()
                .stream()
                .map(noticiaMapper::toDto)
                .toList();
    }

    public NoticiaDto findById(Integer id){
        Noticia noticia = noticiaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        HttpMessage.NOT_FOUND_NOTICE
                ));
        return noticiaMapper.toDto(noticia);
    }

    public NoticiaDto create(NoticiaCreateUpdateDto dto, MultipartFile img){
        Noticia noticia = Noticia.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .fecha(dto.getFecha()).build();

        if (img != null && !img.isEmpty()){
            String imgPath = imageService.saveImage(img);
            noticia.setImage_id(imgPath);
        }
        return noticiaMapper.toDto(noticiaRepository.save(noticia));
    }

    public NoticiaDto update(NoticiaCreateUpdateDto dto, Integer id,  MultipartFile img){
        Noticia noticia = noticiaRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        HttpMessage.NOT_FOUND_NOTICE
                ));
        noticia.setTitle(dto.getTitle());
        noticia.setDescription(dto.getDescription());
        noticia.setFecha(dto.getFecha());

        if(img !=null && !img.isEmpty()){
            String imgPath = imageService.saveImage(img);
            imageService.deleteImageByPath(noticia.getImage_id());
            noticia.setImage_id(imgPath);
        }
        return noticiaMapper.toDto(noticiaRepository.save(noticia));
    }

    public void delete(Integer id){
        if(!noticiaRepository.existsById(id)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    HttpMessage.NOT_FOUND_NOTICE
            );
        }
        noticiaRepository.deleteById(id);
    }
}
