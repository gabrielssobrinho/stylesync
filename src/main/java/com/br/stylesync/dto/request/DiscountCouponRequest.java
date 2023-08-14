package com.br.stylesync.dto.request;

import com.br.stylesync.enums.DiscountCouponType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.br.stylesync.model.DiscountCoupon}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record DiscountCouponRequest(@NotBlank String expirationDate, @NotNull Double minValue,
                                    @NotBlank DiscountCouponType type, @NotNull Double value,
                                    @NotNull String code) implements Serializable {
}
