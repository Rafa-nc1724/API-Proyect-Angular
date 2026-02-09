package com.example.apihermandad.infrastructure.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.example.apihermandad.application.dto.AssetDto;
import com.example.apihermandad.application.services.AssetService;

@RestController
@RequestMapping("/api/asset")
@CrossOrigin(origins = "http://localhost:4200")
class AssetController {
    private AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    /*
     * La lista la pueden ver los tras puesto que
     * el admin tiene todos los permisos
     * la junta puede ver la lista de usuarios
     * el capataz necesita la lista de usuarios para
     * hacer los cambios de usuario normal a costalero
     */
    @GetMapping("/all")
    public ResponseEntity<List<AssetDto>> findAll() {
        return ResponseEntity.ok(assetService.findAll());
    }

    // ver un usuario concreto solo el admin y junta
    @GetMapping("/{id}")
    public ResponseEntity<AssetDto> findById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(assetService.findById(id));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // ver un usuario concreto solo el admin y junta
    @GetMapping("/view/{id}")
    public ResponseEntity<byte[]> findAndViewById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(assetService.viewById(id));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // crear solo admin y junta
    @PostMapping
    public ResponseEntity<AssetDto> create(@RequestParam MultipartFile file) {
        try {
            return ResponseEntity.ok(assetService.create(file));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    // invalidar usuario solo admin y junta
    // @DeleteMapping("/{id}")
    // @PreAuthorize("hasAnyRole('ADMIN','JUNTA')")
    // public ResponseEntity<Void> delete(@PathVariable Integer id) {
    // assetService.delete(id);
    // return ResponseEntity.noContent().build();
    // }

}
