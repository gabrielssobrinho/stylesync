package com.br.stylesync.repository;

import com.br.stylesync.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    boolean existsByCpf(String cpf);
    Boolean existsByEmail(String email);
}
