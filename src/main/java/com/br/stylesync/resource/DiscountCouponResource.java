package com.br.stylesync.resource;

import com.br.stylesync.dto.request.DiscountCouponRequest;
import com.br.stylesync.dto.response.ApiResponse;
import com.br.stylesync.service.DiscountCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/discount-coupon")
@RestController
public class DiscountCouponResource {

    @Autowired
    private DiscountCouponService discountCouponService;

    @PostMapping
    public ResponseEntity<ApiResponse> createDiscountCoupon(@RequestBody DiscountCouponRequest request) {
        return discountCouponService.createDiscountCoupon(request);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllDiscountCoupon() {
        return discountCouponService.getAllDiscountCoupon();
    }

    @GetMapping("/{code}")
    public ResponseEntity<ApiResponse> getDiscountCouponByCode(@PathVariable String code) {
        return discountCouponService.getDiscountCouponByCode(code);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> updateDiscountCoupon(@RequestBody DiscountCouponRequest request, @PathVariable UUID id) {
        return discountCouponService.updateDiscountCoupon(request, id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteDiscountCoupon(@PathVariable UUID id) {
        return discountCouponService.deleteDiscountCoupon(id);
    }
}
