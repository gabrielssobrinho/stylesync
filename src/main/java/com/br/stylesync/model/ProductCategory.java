package com.br.stylesync.model;

import com.br.stylesync.model.AuditEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "product_category")
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory extends AuditEntity {

    @Column(nullable = false)
    private String name;
}
