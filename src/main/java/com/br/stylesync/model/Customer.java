package com.br.stylesync.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();
}
