package com.br.stylesync.enums;

import com.br.stylesync.model.AuditEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "product_category")
@ToString
public class ProductCategory extends AuditEntity {

    @Column(nullable = false)
    private String name;
}
