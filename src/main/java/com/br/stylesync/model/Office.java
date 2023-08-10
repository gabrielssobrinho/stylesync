package com.br.stylesync.model;

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

    private String name;

    
}
