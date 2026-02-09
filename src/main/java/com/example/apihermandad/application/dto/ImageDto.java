package com.example.apihermandad.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.apihermandad.domain.entity.Image}
 */
@Value
public class ImageDto implements Serializable {
    Integer id;
    @NotNull
    String imageBase64;
}