package com.br.stylesync.repository;

import com.br.stylesync.model.DiscountCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DiscountCouponRepository extends JpaRepository<DiscountCoupon, UUID> {
}
