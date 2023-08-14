package com.br.stylesync.model;

import com.br.stylesync.dto.CustomerUpdateDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer extends AuditEntity{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private Date birthDate;

    @Column(nullable = false, length = 11)
    private String phone;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String address;

    private Boolean active;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    public void update(CustomerUpdateDto request) {
        this.name = request.name();
        this.email = request.email();
        this.phone = request.phone();
        this.address = request.address();
    }
}
