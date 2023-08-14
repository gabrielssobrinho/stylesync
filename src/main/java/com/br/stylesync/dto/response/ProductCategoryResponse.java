package com.br.stylesync.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link com.br.stylesync.model.ProductCategory}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ProductCategoryResponse(String name, UUID id) implements Serializable {
}
