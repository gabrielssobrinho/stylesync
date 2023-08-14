package com.br.stylesync.model;

import com.br.stylesync.dto.CustomerUpdateDto;
import com.br.stylesync.dto.UpdateProductDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "product")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    public void update(UpdateProductDto request) {
        name = request.name();
        brand = request.brand();
        size = request.size();
        price = request.price();
        variation = request.variation();
        discount = request.discount();
        quantity = request.quantity();
    }
}
