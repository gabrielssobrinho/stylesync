package com.br.stylesync.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image extends AuditEntity {
    @Column(name = "type")
    private String type;

    @Column(name = "image", nullable = false, length = 100000)
    private byte[] image;
}

