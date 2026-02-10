package com.example.apihermandad.application.services;

import com.example.apihermandad.domain.entity.Image;
import com.example.apihermandad.domain.repository.ImageRepository;
import com.example.apihermandad.utils.MethodUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.UUID;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    private String generateUniqueName(String originalName) {
        return UUID.randomUUID() + "_" + originalName;
    }

    public record ImageData(String contentType, byte[] bytes) {}

    public String saveImage(MultipartFile file) {

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

            Image saved = imageRepository.save(image);

            return "/image/" + saved.getId();

        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al procesar la imagen"
            );
        }
    }

    public ImageData loadImage(Integer id) {

        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Imagen no encontrada"
                ));

        try {
            byte[] bytes = MethodUtils.descomprimirDeBase64(image.getBase64());
            return new ImageData(image.getType(), bytes);

        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al descomprimir la imagen"
            );
        }
    }

   public void deleteImageByPath(String imgPath){
        if(imgPath == null || imgPath.isBlank()){
            return;
        }
        String[] parts = imgPath.split("/");
        if(parts.length !=3){
            return;
        }
        try{
            Integer imgId = Integer.valueOf(parts[2]);
            imageRepository.deleteById(imgId);
        }catch (NumberFormatException ignored){}
   }
}