package com.br.stylesync.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order extends AuditEntity{

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "orderDate")
    private Date orderDate;

    @Column(name = "totalAmount")
    private Double totalAmount = 0d;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "discount_coupon_id")
    private DiscountCoupon discountCoupon;

    public void calculateTotalAmount(){
        for (Product product : this.products) {
            this.totalAmount += product.getPrice();
        }
    }
}
