package com.br.stylesync.dto.response;

import com.br.stylesync.model.Product;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public record ProductResponse(
        String code,
        String name,
        String brand,
        String size,
        Double price,
        String variation,
        String discount,
        Integer quantity
) implements Serializable {
    public ProductResponse(Product productSaved) {
        this(
                productSaved.getCode(),
                productSaved.getName(),
                productSaved.getBrand(),
                productSaved.getSize(),
                productSaved.getPrice(),
                productSaved.getVariation(),
                productSaved.getDiscount(),
                productSaved.getQuantity());
    }
}
