package com.br.stylesync.dto;

import jakarta.persistence.Column;
import lombok.Getter;

public record UpdateProductDto(
        String name,
        String brand,
        String size,
        Double price,
        String variation,
        String discount,
        Integer quantity
) {
}
