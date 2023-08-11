package com.br.stylesync.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public record ProductResponse (
        Long code,
        String name,
        String brand,
        String size,
        Double price,
        String variation,
        String discount,
        Integer quantity
) implements Serializable {
}
