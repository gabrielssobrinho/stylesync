package com.br.stylesync.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link com.br.stylesync.model.Employee}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record SignInRequest(@NotBlank String email, @NotBlank String password) implements Serializable {
}
