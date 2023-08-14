package com.br.stylesync.service;

import com.br.stylesync.dto.request.DiscountCouponRequest;
import com.br.stylesync.dto.response.ApiResponse;
import com.br.stylesync.dto.response.DiscountCouponResponse;
import com.br.stylesync.enums.DiscountCouponType;
import com.br.stylesync.model.DiscountCoupon;
import com.br.stylesync.model.Employee;
import com.br.stylesync.repository.DiscountCouponRepository;
import com.br.stylesync.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class DiscountCouponService {

    @Autowired
    private DiscountCouponRepository discountCouponRepository;

    public ResponseEntity<ApiResponse> createDiscountCoupon(DiscountCouponRequest request) {
        if (request.type().equals(DiscountCouponType.PERCENTAGE)) {
            if (request.value() > 1 || request.value() <= 0) {
                return ResponseEntity.badRequest().body(new ApiResponse("Invalid value for percentage discount coupon", null));
            }
        }
        if (DateUtils.parse(request.expirationDate()).toInstant().isBefore(new Date().toInstant())) {
            return ResponseEntity.badRequest().body(new ApiResponse("Invalid expiration date", null));
        }
        if(discountCouponRepository.existsByCode(request.code())){
            return ResponseEntity.badRequest().body(new ApiResponse("Discount coupon already exists", null));
        }
        DiscountCoupon discountCoupon = DiscountCoupon.builder()
                .code(request.code())
                .expirationDate(DateUtils.parse(request.expirationDate()))
                .minValue(request.minValue())
                .type(request.type())
                .value(request.value())
                .build();
        discountCoupon.setCreatedBy(Employee.currentUser().getName());
        discountCouponRepository.save(discountCoupon);
        return ResponseEntity.ok(new ApiResponse("Discount coupon created successfully", new DiscountCouponResponse(discountCoupon)));
    }

    public ResponseEntity<ApiResponse> getAllDiscountCoupon() {
        return ResponseEntity.ok(new ApiResponse("Discount coupon list", discountCouponRepository.findAll().stream().map(DiscountCouponResponse::new).toList()));
    }


    public ResponseEntity<ApiResponse> getDiscountCouponByCode(String code) {
        DiscountCoupon discountCoupon = discountCouponRepository.findByCode(code).orElseThrow(() -> new RuntimeException("Discount coupon not found"));
        if (discountCoupon == null) {
            return ResponseEntity.badRequest().body(new ApiResponse("Discount coupon not found", null));
        }
        return ResponseEntity.ok(new ApiResponse("Discount coupon", new DiscountCouponResponse(discountCoupon)));
    }

    public ResponseEntity<ApiResponse> updateDiscountCoupon(DiscountCouponRequest request, UUID id) {
        return null;
    }

    public ResponseEntity<ApiResponse> deleteDiscountCoupon(UUID id) {
        DiscountCoupon discountCoupon = discountCouponRepository.findById(id).orElseThrow(() -> new RuntimeException("Discount coupon not found"));
        discountCouponRepository.delete(discountCoupon);
        return ResponseEntity.ok(new ApiResponse("Discount coupon deleted successfully", null));
    }
}
