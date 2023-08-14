package com.br.stylesync.dto.request;

import jakarta.validation.constraints.NotBlank;

public record OfficeRequest(@NotBlank String name,
                            @NotBlank String description) {
}
