package com.br.stylesync.dto.response;

import com.br.stylesync.enums.DiscountCouponType;
import com.br.stylesync.model.DiscountCoupon;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * DTO for {@link com.br.stylesync.model.DiscountCoupon}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record DiscountCouponResponse(UUID id, Date expirationDate, Double minValue, DiscountCouponType type,
                                     Double value, String code) implements Serializable {
    public DiscountCouponResponse(DiscountCoupon discountCoupon) {
        this(discountCoupon.getId(), discountCoupon.getExpirationDate(), discountCoupon.getMinValue(),
                discountCoupon.getType(), discountCoupon.getValue(), discountCoupon.getCode());
    }
}
