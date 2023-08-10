package com.br.stylesync.model;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscountCoupon extends AuditEntity{

    private Date expirationDate;
    private Double minValue;
    private String type;
    private Double value;
    private String code;

    public boolean isExpired() {
        return false;
    }

}
