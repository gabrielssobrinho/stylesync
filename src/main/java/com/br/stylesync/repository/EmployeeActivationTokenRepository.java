package com.br.stylesync.repository;

import com.br.stylesync.model.EmployeeActivationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeeActivationTokenRepository extends JpaRepository<EmployeeActivationToken, UUID> {
}
