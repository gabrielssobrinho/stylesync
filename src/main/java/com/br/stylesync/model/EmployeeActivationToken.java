package com.br.stylesync.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class EmployeeActivationToken {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private UUID id;

    private LocalDateTime expireDate;

    @OneToOne
    private Employee employee;

    public EmployeeActivationToken(Employee employee) {
        this.employee = employee;
        this.expireDate = LocalDateTime.now().plusMinutes(30);
    }

    public Boolean isExpired() {
        return LocalDateTime.now().isAfter(this.expireDate);
    }
}
