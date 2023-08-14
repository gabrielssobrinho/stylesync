package com.br.stylesync.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "product")
@ToString
public class Product extends AuditEntity {

    @Column(name = "sku", unique = true)
    private Long code;

    @Column(name = "name")
    private String name;

    @Column(name = "brand")
    private String brand;

    @Column(name = "size")
    private String size;

    @Column(name = "price")
    private Double price;

    @Column(name = "variation")
    private String variation;

    @Column(name = "discount")
    private String discount;

    @Column(name = "quantity")
    private Integer quantity;

    @ToString.Exclude
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_category_id", nullable = false)
    private ProductCategory productCategory;

}
