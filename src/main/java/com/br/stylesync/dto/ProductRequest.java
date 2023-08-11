package com.br.stylesync.dto;

import com.br.stylesync.model.AuditEntity;
import jakarta.validation.constraints.NotBlank;

public record ProductRequest(
        @NotBlank
        Long code,
        @NotBlank
        String name,
        @NotBlank
        String brand,
        @NotBlank
        String variation


) {
}
