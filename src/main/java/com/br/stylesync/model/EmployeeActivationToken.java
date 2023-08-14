package com.br.stylesync.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    public EmployeeActivationToken(LocalDateTime expireDate) {
        this.expireDate = expireDate.plusMinutes(30);
    }

    public Boolean isExpired() {
        return LocalDateTime.now().isAfter(this.expireDate);
    }
}
