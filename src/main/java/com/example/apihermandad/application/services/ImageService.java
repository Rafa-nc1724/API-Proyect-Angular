package com.example.apihermandad.application.services;

import com.example.apihermandad.domain.entity.Image;
import com.example.apihermandad.domain.repository.ImageRepository;
import com.example.apihermandad.utils.HttpMessage;
import com.example.apihermandad.utils.MethodUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;


    public record ImageData(String contentType, byte[] bytes) {}


    public Image saveImage(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El archivo de imagen es obligatorio"
            );
        }

        String contentType = file.getContentType();
        if (!"image/png".equals(contentType) && !"image/jpeg".equals(contentType)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Solo se permiten imágenes PNG o JPEG"
            );
        }

        try {
            byte[] bytes = file.getBytes();
            String base64Compressed = MethodUtils.comprimirABase64(bytes);

            Image image = new Image();
            image.setName(generateUniqueName(file.getOriginalFilename()));
            image.setType(contentType);
            image.setBase64(base64Compressed);

            return imageRepository.save(image);

        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al procesar la imagen"
            );
        }
    }

    public Optional<Image> getImageById(Integer imageId) {
        return imageRepository.findById(imageId);

    }

    public ImageData loadImage(Integer id) {

        Optional<Image> image = getImageById(id);

        if (image.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    HttpMessage.NOT_FOUND_IMG);
        }

        try {
            byte[] bytes = MethodUtils.descomprimirDeBase64(image.get().getBase64());
            return new ImageData(image.get().getType(), bytes);

        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al descomprimir la imagen"
            );
        }
    }

   public void deleteImage(Image img){
        if(img == null){
            return;
        }

        if (imageRepository.existsById(img.getId())) {
            imageRepository.deleteById(img.getId());
        }
   }

    private String generateUniqueName(String originalName) {
        return UUID.randomUUID() + "_" + originalName;
    }

}