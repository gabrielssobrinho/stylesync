package com.br.stylesync.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

public record EmployeeRequest(@NotBlank String name,
                              @NotBlank String email,
                              @NotBlank String password,
                              @NotNull String birthDate,
                              @NotBlank String phone,
                              @NotBlank String address,
                              @NotNull UUID officeId,
                              MultipartFile profileImage) {
}
