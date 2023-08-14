package com.br.stylesync.service;

import com.br.stylesync.dto.UpdateProductDto;
import com.br.stylesync.dto.request.ProductRequest;
import com.br.stylesync.dto.response.ApiResponse;
import com.br.stylesync.dto.response.ProductResponse;
import com.br.stylesync.model.Employee;
import com.br.stylesync.model.Product;
import com.br.stylesync.model.ProductCategory;
import com.br.stylesync.repository.ProductCategoryRepository;
import com.br.stylesync.repository.ProductRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRespository productRespository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public ResponseEntity<ApiResponse> getProduct(UUID id) {
        Optional<Product> productO = productRespository.findById(id);
        return productO.map(product -> ResponseEntity.ok().body(new ApiResponse("Produto encontrado", product)))
                .orElseGet(() -> ResponseEntity.badRequest().body(new ApiResponse("Produto não encontrado", id)));
    }

    public ResponseEntity<ApiResponse> saveProduct(ProductRequest product) {
        ProductCategory productCategory = productCategoryRepository.findById(product.categoryId()).orElseThrow(() -> new RuntimeException("Category not Found"));

        Product productSaved = Product.builder()
                .name(product.name())
                .price(product.price())
                .productCategory(productCategory)
                .brand(product.brand())
                .code(product.code())
                .size(product.size())
                .quantity(product.quantity())
                .discount(product.discount())
                .variation(product.variation())
                .build();

        productSaved.setCreatedBy(Employee.currentUser().getName());
        productRespository.save(productSaved);
        return ResponseEntity.ok().body(new ApiResponse("Product with Success", new ProductResponse(productSaved)));
    }
    public ResponseEntity<ApiResponse> updateProduct(UUID id, UpdateProductDto productRequest){
        Product product = productRespository.findById(id).orElse(null);

        if(product == null){
            return ResponseEntity.ok().body(new ApiResponse("Product not found", id));
        }

        product.update(productRequest);
        productRespository.save(product);
        return ResponseEntity.ok(new ApiResponse("Product updated successfully", new ProductResponse(product)));
    }
    public List<Product> findAllProducts(){
        return productRespository.findAll();
    }

    public ResponseEntity<ApiResponse> deleteProduct(UUID  id){
        Product product = productRespository.findById(id).orElseThrow(null);
        if(product == null){
            return ResponseEntity.ok().body(new ApiResponse("Product not found", id));
        }
        productRespository.delete(product);
        return ResponseEntity.ok().body(new ApiResponse("Product successfully deleted", id));
    }
}
