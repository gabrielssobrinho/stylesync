package com.br.stylesync.dto.response;

import com.br.stylesync.model.Customer;
import com.br.stylesync.model.DiscountCoupon;
import com.br.stylesync.model.Order;
import com.br.stylesync.model.Product;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link com.br.stylesync.model.Order}
 */
public record OrderResponse(UUID id, Customer customer, Date orderDate, Double totalAmount, List<Product> products,
                            DiscountCoupon discountCoupon) implements Serializable {
    public OrderResponse(Order order) {
        this(order.getId(), order.getCustomer(), order.getOrderDate(), order.getTotalAmount(), order.getProducts(),
                order.getDiscountCoupon());
    }
}