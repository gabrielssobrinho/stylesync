package com.br.stylesync.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.br.stylesync.model.Customer}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record CustomerRequest(@NotBlank String name, @Email @NotBlank String email, @NotBlank String birthDate, String phone,
                              String cpf, String address) implements Serializable {
}
