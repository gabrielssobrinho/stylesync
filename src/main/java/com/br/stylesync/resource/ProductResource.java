package com.br.stylesync.resource;

import com.br.stylesync.dto.ApiResponse;
import com.br.stylesync.model.Product;
import com.br.stylesync.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductResource {
    private ProductService productService;

    @GetMapping("{id}")
    public DeferredResult<ResponseEntity<ApiResponse>> getProduct(@PathVariable UUID id) {
        final DeferredResult<ResponseEntity<ApiResponse>> deferredResult = new DeferredResult<>();
        deferredResult.setResult(this.productService.getProduct(id));
        return deferredResult;
    }
}
