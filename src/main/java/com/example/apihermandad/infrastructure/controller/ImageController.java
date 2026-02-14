package com.example.apihermandad.infrastructure.controller;

import com.example.apihermandad.application.services.ImageService;
import com.example.apihermandad.domain.entity.Image;
import com.example.apihermandad.domain.repository.ImageRepository;
import com.example.apihermandad.utils.HttpMessage;
import com.example.apihermandad.utils.MethodUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
@RequestMapping("/api/image")
public class ImageController {

    private final ImageService imageService;
    private final ImageRepository imageRepository;

    public ImageController(ImageService imageService, ImageRepository imageRepository) {
        this.imageService = imageService;
        this.imageRepository = imageRepository;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Integer> createImage(
            @RequestPart MultipartFile image
    ) {
        Image savedImage = imageService.saveImage(image);
        return ResponseEntity.ok(savedImage.getId());
    }


    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Integer id) throws IOException {
        Image img = imageRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        HttpMessage.NOT_FOUND_IMG
                ));

        byte[] imageByte =
                MethodUtils.descomprimirDeBase64(img.getBase64());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(img.getType()))
                .body(imageByte);
    }
}