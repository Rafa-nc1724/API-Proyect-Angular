package com.example.apihermandad.application.services;

import com.example.apihermandad.domain.entity.Image;
import com.example.apihermandad.domain.repository.ImageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.GZIPOutputStream;


@Service
    public class ImageService {

        private final ImageRepository imageRepository;

        public ImageService(ImageRepository imageRepository) {
            this.imageRepository = imageRepository;
        }

        public Image saveImage(MultipartFile file) {

            if (file.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Imagen vacía");
            }

            String contentType = file.getContentType();
            if (!"image/jpeg".equals(contentType) && !"image/png".equals(contentType)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato no permitido");
            }

            try {
                byte[] compressed = compress(file.getBytes());
                String base64 = Base64.getEncoder().encodeToString(compressed);

                Image image = new Image();
                image.setImageBase64(base64);
                return imageRepository.save(image);

            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error procesando imagen");
            }
        }

        public String getImageBase64(Integer id) {
            return imageRepository.findById(id)
                    .map(Image::getImageBase64)
                    .orElseThrow(() ->
                            new ResponseStatusException(HttpStatus.NOT_FOUND, "Imagen no encontrada")
                    );
        }

        private byte[] compress(byte[] data) throws IOException {
            // Compresión simple GZIP (suficiente y segura)
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            GZIPOutputStream gzip = new GZIPOutputStream(bos);
            gzip.write(data);
            gzip.close();
            return bos.toByteArray();
        }
    }

