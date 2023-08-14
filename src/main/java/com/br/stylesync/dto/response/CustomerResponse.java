package com.br.stylesync.dto.response;

import com.br.stylesync.model.Customer;
import com.br.stylesync.model.Order;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link com.br.stylesync.model.Customer}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record CustomerResponse(UUID id, String name, String email, Date birthDate, String phone, String cpf,
                               String address, List<Order> orders) implements Serializable {

    public CustomerResponse(Customer customer){
        this(customer.getId(), customer.getName(), customer.getEmail(), customer.getBirthDate(), customer.getPhone(),
                customer.getCpf(), customer.getAddress(), customer.getOrders());
    }
}
