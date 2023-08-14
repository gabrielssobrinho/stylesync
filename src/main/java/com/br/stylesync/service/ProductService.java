package com.br.stylesync.service;

import com.br.stylesync.dto.request.ProducRequest;
import com.br.stylesync.dto.response.ApiResponse;
import com.br.stylesync.model.Product;
import com.br.stylesync.repository.ProductRespository;
import org.springframework.beans.BeanUtils;
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

    public ResponseEntity<ApiResponse> saveProduct(ProducRequest product) {
        Product productSaved = new Product();
        BeanUtils.copyProperties(product, productSaved);
        return ResponseEntity.ok().body(new ApiResponse("Produto salvo com sucesso", productSaved));
    }
}
