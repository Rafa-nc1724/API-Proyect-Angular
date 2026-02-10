package com.example.apihermandad.infrastructure.controller;

import com.example.apihermandad.application.services.ImageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    /**
     * Endpoint público
     * GET /image/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Integer id) {

        var imageData = imageService.loadImage(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, imageData.contentType())
                .body(imageData.bytes());
    }
}