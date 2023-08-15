package com.br.stylesync.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link com.br.stylesync.model.Order}
 */
public record OrderRequest(@NotBlank String customerCpf, @NotNull List<UUID> productIds,
                           @NotNull String discountCouponCode) implements Serializable {
}