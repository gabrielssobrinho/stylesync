package com.br.stylesync.service;

import com.br.stylesync.dto.request.CustomerRequest;
import com.br.stylesync.dto.response.ApiResponse;
import com.br.stylesync.dto.response.CustomerResponse;
import com.br.stylesync.model.Customer;
import com.br.stylesync.model.Employee;
import com.br.stylesync.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public ResponseEntity<ApiResponse> saveCustomer(CustomerRequest request) {
        if(customerRepository.existsByEmail(request.email())){
            return ResponseEntity.badRequest().body(new ApiResponse("Email already exists", null));
        }
        if(customerRepository.existsByCpf(request.cpf())){
            return ResponseEntity.badRequest().body(new ApiResponse("CPF already exists", null));
        }
        Customer customer;
        try{
            customer = Customer.builder().name(request.name()).email(request.email()).birthDate(sdf.parse(request.birthDate())).phone(request.phone()).cpf(request.cpf()).address(request.address()).build();
        }catch (ParseException e){
            throw new RuntimeException("Error while parsing date");
        }
        customer.setCreatedBy(Employee.currentUser().getName());
        customerRepository.save(customer);

        return ResponseEntity.ok(new ApiResponse("Customer created successfully", new CustomerResponse(customer)));
    }

    public ResponseEntity<ApiResponse> getCustomer(UUID id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        return ResponseEntity.ok(new ApiResponse("Customer found successfully", new CustomerResponse(customer)));
    }

    public ResponseEntity<ApiResponse> getAllCustomers() {
        return ResponseEntity.ok(new ApiResponse("Customers found successfully", customerRepository.findAll().stream().map(CustomerResponse::new).toList()));
    }

    public ResponseEntity<ApiResponse> updateCustomer(UUID id, CustomerRequest request) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));

        if(customerRepository.existsByEmail(request.email()) && !customer.getEmail().equals(request.email())){
            return ResponseEntity.badRequest().body(new ApiResponse("Email already exists", null));
        }

        customer.update(request);
        customerRepository.save(customer);

        return ResponseEntity.ok(new ApiResponse("Customer updated successfully", new CustomerResponse(customer)));
    }

    public ResponseEntity<ApiResponse> deleteCustomer(UUID id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.setActive(false);
        customerRepository.save(customer);
        return ResponseEntity.ok(new ApiResponse("Customer deleted successfully", null));
    }
}
