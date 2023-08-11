package com.br.stylesync.service;

import com.br.stylesync.dto.ApiResponse;
import com.br.stylesync.dto.ProductRequest;
import com.br.stylesync.model.Product;
import com.br.stylesync.repository.ProductRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    ProductRespository productRespository;
    public ResponseEntity<ApiResponse> getProduct(UUID id) {
        Optional<Product> productO = productRespository.findById(id);
        return productO.map(product -> ResponseEntity.ok().body(new ApiResponse("Produto encontrado", product)))
                .orElseGet(() -> ResponseEntity.badRequest().body(new ApiResponse("Produto não encontrado", id)));
    }
}
