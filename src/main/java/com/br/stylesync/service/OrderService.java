package com.br.stylesync.service;

import com.br.stylesync.dto.request.OrderRequest;
import com.br.stylesync.dto.response.ApiResponse;
import com.br.stylesync.dto.response.OrderResponse;
import com.br.stylesync.model.Customer;
import com.br.stylesync.model.Employee;
import com.br.stylesync.model.Order;
import com.br.stylesync.repository.CustomerRepository;
import com.br.stylesync.repository.DiscountCouponRepository;
import com.br.stylesync.repository.OrderRepository;
import com.br.stylesync.repository.ProductRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DiscountCouponRepository discountCouponRepository;

    @Autowired
    private ProductRespository productRespository;

    public ResponseEntity<ApiResponse> saveOrder(OrderRequest orderRequest) {
        Customer customer = customerRepository.findByCpf(orderRequest.customerCpf()).orElseThrow(() -> new RuntimeException("Customer not found"));

        Order order = Order.builder()
                .customer(customer)
                .orderDate(new Date())
                .discountCoupon(discountCouponRepository.findByCode(orderRequest.discountCouponCode()).orElse(null))
                .products(productRespository.findAllById(orderRequest.productIds()))
                .build();
        order.setTotalAmount(0.0);
        order.setCreatedBy(Employee.currentUser().getName());
        order.calculateTotalAmount();

        for (UUID productId : orderRequest.productIds()) {
            productRespository.findById(productId).ifPresent(product -> {
                if (product.getQuantity() > 1) {
                    product.setQuantity(product.getQuantity() - 1);
                    productRespository.save(product);
                } else {
                    throw new RuntimeException("Product out of stock");
                }
            });
        }
        order.setId(UUID.randomUUID());
        orderRepository.save(order);

        return ResponseEntity.ok(new ApiResponse("Order saved successfully", new OrderResponse(order)));
    }

    public ResponseEntity<ApiResponse> getOrders() {
        return ResponseEntity.ok(new ApiResponse("Orders retrieved successfully", orderRepository.findAll().stream().map(OrderResponse::new).toList()));
    }

    public ResponseEntity<ApiResponse> getOrder(UUID id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        return ResponseEntity.ok(new ApiResponse("Order retrieved successfully", new OrderResponse(order)));
    }
}
