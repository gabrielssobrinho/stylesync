package com.br.stylesync.resource;

import com.br.stylesync.dto.request.ProductCategoryRequest;
import com.br.stylesync.dto.response.ApiResponse;
import com.br.stylesync.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/category")
public class ProductCategoryResource {

    @Autowired
    private ProductCategoryService categoryService;

    @PostMapping
    public ResponseEntity<ApiResponse> createProductCategory(@RequestBody ProductCategoryRequest request) {
        return categoryService.createProductCategory(request);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllProductCategories() {
        return categoryService.getAllProductCategories();
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> getProductCategoryById(@PathVariable UUID id) {
        return categoryService.getProductCategoryById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> updateProductCategory(@PathVariable UUID id, @RequestBody ProductCategoryRequest request) {
        return categoryService.updateProductCategory(id, request);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteProductCategory(@PathVariable UUID id) {
        return categoryService.deleteProductCategory(id);
    }
}
