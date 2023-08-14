package com.br.stylesync.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link com.br.stylesync.model.Product}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ProducRequest(@NotNull @Positive Long code, @NotEmpty String name, @NotBlank String brand,
                            @NotBlank String size, @NotNull @Positive Double price, @NotBlank String variation,
                            @NotBlank String discount,
                            @NotNull @PositiveOrZero Integer quantity,
                            @NotNull UUID categoryId) implements Serializable {
}
