package com.br.stylesync.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link com.br.stylesync.model.Customer}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record CustomerUpdateDto(String name, String email, String birthDate, String phone,
                                String cpf, String address) implements Serializable {
}
