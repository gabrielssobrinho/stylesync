package com.br.stylesync.dto.response;

import com.br.stylesync.model.Employee;
import com.br.stylesync.model.Image;
import com.br.stylesync.model.Office;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public record EmployeeResponse(UUID id, String name, String email, Date birthDate, String phone, String address,
                               Office office, Image profileImage) implements Serializable {

    public EmployeeResponse(Employee employee){
        this(employee.getId(), employee.getName(), employee.getEmail(), employee.getBirthDate(), employee.getPhone(),
                employee.getAddress(), employee.getOffice(), employee.getProfileImage());
    }
}
