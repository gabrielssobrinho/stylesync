package com.br.stylesync.repository;

import com.br.stylesync.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, UUID> {
    boolean existsByName(String name);
}
