package com.br.stylesync.resource;

import com.br.stylesync.dto.UpdateProductDto;
import com.br.stylesync.dto.request.ProductRequest;
import com.br.stylesync.dto.response.ApiResponse;
import com.br.stylesync.model.Product;
import com.br.stylesync.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse> saveProduct(@RequestBody ProductRequest request) {
        return this.productService.saveProduct(request);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> getProduct(@PathVariable UUID id) {
        return this.productService.getProduct(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable UUID id, @RequestBody UpdateProductDto updateProductDto) {
        return this.productService.updateProduct(id, updateProductDto);
    }
    @GetMapping
    public List<Product> findAllProducts(){
        return this.productService.findAllProducts();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable UUID id){
        return this.productService.deleteProduct(id);
    }
}
