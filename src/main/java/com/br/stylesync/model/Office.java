package com.br.stylesync.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Office extends AuditEntity {

    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String description;
}
