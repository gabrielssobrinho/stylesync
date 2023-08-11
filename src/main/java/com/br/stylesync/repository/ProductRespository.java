package com.br.stylesync.repository;

import com.br.stylesync.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRespository extends JpaRepository<Product, UUID> {
}
