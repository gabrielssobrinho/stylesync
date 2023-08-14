package com.br.stylesync.service;

import com.br.stylesync.dto.request.ProductCategoryRequest;
import com.br.stylesync.dto.response.ApiResponse;
import com.br.stylesync.model.ProductCategory;
import com.br.stylesync.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository categoryRepository;

    public ResponseEntity<ApiResponse> createProductCategory(ProductCategoryRequest request) {
        if (categoryRepository.existsByName(request.name())){
            return ResponseEntity.badRequest().body(new ApiResponse("Category already exists", null));
        }
        ProductCategory category = new ProductCategory(request.name());
        category.setCreatedBy("admin");
        return ResponseEntity.ok().body(new ApiResponse("Category created successfully", categoryRepository.save(category)));
    }

    public ResponseEntity<ApiResponse> getAllProductCategories() {
        return ResponseEntity.ok().body(new ApiResponse("Categories fetched successfully", categoryRepository.findAll()));
    }

    public ResponseEntity<ApiResponse> getProductCategoryById(UUID id) {
        return ResponseEntity.ok().body(new ApiResponse("Category fetched successfully", categoryRepository.findById(id)));
    }

    public ResponseEntity<ApiResponse> updateProductCategory(UUID id, ProductCategoryRequest request) {
        ProductCategory category = categoryRepository.findById(id).orElse(null);
        if(category == null){
            return ResponseEntity.badRequest().body(new ApiResponse("Category not found", null));
        }
        if(!Objects.equals(request.name(), category.getName())){
            category.setName(request.name());
        }
        return ResponseEntity.ok().body(new ApiResponse("Category updated successfully", categoryRepository.save(category)));
    }

    public ResponseEntity<ApiResponse> deleteProductCategory(UUID id) {
        ProductCategory category = categoryRepository.findById(id).orElse(null);
        if(category == null){
            return ResponseEntity.badRequest().body(new ApiResponse("Category not found", null));
        }
        categoryRepository.delete(category);
        return ResponseEntity.ok().body(new ApiResponse("Category deleted successfully", null));
    }
}
