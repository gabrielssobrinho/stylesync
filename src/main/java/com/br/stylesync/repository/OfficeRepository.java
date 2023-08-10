package com.br.stylesync.repository;

import com.br.stylesync.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OfficeRepository extends JpaRepository<Office, UUID> {
    boolean existsByName(String name);
}
