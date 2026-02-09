package com.example.apihermandad.application.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for {@link com.example.apihermandad.domain.entity.Noticia}
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssetCreateDto {
    Integer id;
    String name;
    String type;
    byte[] data;
    LocalDateTime createTime;
}