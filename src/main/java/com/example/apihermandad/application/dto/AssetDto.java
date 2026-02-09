package com.example.apihermandad.application.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Value;

/**
 * DTO for {@link com.example.apihermandad.domain.entity.Noticia}
 */
@Value
public class AssetDto implements Serializable {
    Integer id;
    String name;
    String type;
    String url;
    LocalDateTime createTime;
}