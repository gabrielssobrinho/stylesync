package com.br.stylesync.resource;

import com.br.stylesync.dto.request.ProducRequest;
import com.br.stylesync.dto.response.ApiResponse;
import com.br.stylesync.model.Product;
import com.br.stylesync.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @PostMapping
    public DeferredResult<ResponseEntity<ApiResponse>> saveProduct(@RequestBody ProducRequest request) {
        final DeferredResult<ResponseEntity<ApiResponse>> deferredResult = new DeferredResult<>();
        deferredResult.setResult(this.productService.saveProduct(request));
        return deferredResult;
    }

    @GetMapping("{id}")
    public DeferredResult<ResponseEntity<ApiResponse>> getProduct(@PathVariable UUID id) {
        final DeferredResult<ResponseEntity<ApiResponse>> deferredResult = new DeferredResult<>();
        deferredResult.setResult(this.productService.getProduct(id));
        return deferredResult;
    }
}
