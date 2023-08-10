package com.br.stylesync.dto;

import jakarta.validation.constraints.NotBlank;

public record OfficeRequest(@NotBlank String name,
                            @NotBlank String description) {
}
