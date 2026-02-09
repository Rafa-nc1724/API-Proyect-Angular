package com.example.apihermandad.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class CompressionTools {

    // Para prevenir que alguien instancie la clase
    private CompressionTools() {}

    // Comprimir bytes de imagen -> String base64 comprimido (para BD)
    public static String comprimirABase64(byte[] bytesImagen) throws IOException {
        // 1. Comprimir bytes con GZIP
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (GZIPOutputStream gzip = new GZIPOutputStream(baos)) {
            gzip.write(bytesImagen);
        }

        // 2. Codificar a base64 (java.util.Base64 nativo, rápido en Java 17)
        byte[] comprimido = baos.toByteArray();
        return Base64.getEncoder().encodeToString(comprimido);
    }

    // Descomprimir base64 -> bytes originales (para devolver imagen)
    public static byte[] descomprimirDeBase64(String base64Comprimido) throws IOException {
        // 1. Decodificar base64
        byte[] comprimido = Base64.getDecoder().decode(base64Comprimido);

        // 2. Descomprimir GZIP
        ByteArrayInputStream bais = new ByteArrayInputStream(comprimido);
        try (GZIPInputStream gzip = new GZIPInputStream(bais);
                ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[8192];
            int len;
            while ((len = gzip.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            return baos.toByteArray();
        }
    }
}
