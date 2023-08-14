package com.br.stylesync.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public record UpdateEmployeeDto(String name,
                                String email,
                                String password,
                                String birthDate,
                                String phone,
                                String address,
                                UUID officeId,
                                MultipartFile profileImage) {
}
