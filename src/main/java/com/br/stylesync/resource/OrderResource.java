package com.br.stylesync.resource;

import com.br.stylesync.dto.request.OrderRequest;
import com.br.stylesync.dto.response.ApiResponse;
import com.br.stylesync.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderResource {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse> saveOrder(@RequestBody OrderRequest orderRequest){
        return orderService.saveOrder(orderRequest);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getOrders(){
        return orderService.getOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getOrder(@PathVariable("id") UUID id){
        return orderService.getOrder(id);
    }
}
