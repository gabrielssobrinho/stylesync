package com.br.stylesync.resource;

import com.br.stylesync.dto.CustomerUpdateDto;
import com.br.stylesync.dto.request.CustomerRequest;
import com.br.stylesync.dto.response.ApiResponse;
import com.br.stylesync.model.AuditEntity;
import com.br.stylesync.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/customer")
public class CustomerResource extends AuditEntity {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<ApiResponse> saveCustomer(CustomerRequest request){
        return customerService.saveCustomer(request);
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> getCustomer(UUID id){
        return customerService.getCustomer(id);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> updateCustomer(@PathVariable UUID id, CustomerUpdateDto request){
        return customerService.updateCustomer(id, request);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable UUID id){
        return customerService.deleteCustomer(id);
    }
}
