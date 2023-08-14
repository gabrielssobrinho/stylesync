package com.br.stylesync.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link com.br.stylesync.model.ProductCategory}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ProductCategoryRequest(@NotBlank String name) implements Serializable {
}
