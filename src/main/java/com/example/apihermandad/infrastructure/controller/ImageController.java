package com.example.apihermandad.infrastructure.controller;


import com.example.apihermandad.application.services.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @Operation(security = {})
    @GetMapping("/{id}")
    public String getImage(@PathVariable Integer id) {
        return imageService.getImageBase64(id);
    }
}