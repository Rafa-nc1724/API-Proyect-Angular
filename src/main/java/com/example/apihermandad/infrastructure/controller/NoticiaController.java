package com.example.apihermandad.infrastructure.controller;

import com.example.apihermandad.application.dto.NoticiaDto;
import com.example.apihermandad.application.services.NoticiaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NoticiaController {

    private final NoticiaService noticiaService;

    public NoticiaController(NoticiaService noticiaService) {
        this.noticiaService = noticiaService;
    }

    /**
     * GET /api/news
     * Endpoint público
     */
    @GetMapping
    public List<NoticiaDto> getAllNoticias() {
        return noticiaService.getAllNoticias();
    }

}