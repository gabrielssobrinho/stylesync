package com.br.stylesync.service;

import com.br.stylesync.dto.ApiResponse;
import com.br.stylesync.dto.EmployeeRequest;
import com.br.stylesync.dto.EmployeeResponse;
import com.br.stylesync.model.Employee;
import com.br.stylesync.model.Image;
import com.br.stylesync.repository.EmployeeRepository;
import com.br.stylesync.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private OfficeRepository officeRepository;
    @Autowired
    private ImageService imageService;

    public ResponseEntity<ApiResponse> saveEmployee(EmployeeRequest employeeRequest) throws ParseException, IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (employeeRepository.existsByEmail(employeeRequest.email())) {
            return ResponseEntity.badRequest().body(new ApiResponse("Email already exists", employeeRequest.email()));
        }

        Image image = null;

        if (employeeRequest.profileImage() != null) {
            image = imageService.uploadImage(employeeRequest.profileImage());
        }

        Employee employee = Employee.builder()
                .name(employeeRequest.name())
                .email(employeeRequest.email())
                .password(employeeRequest.password())
                .birthDate(sdf.parse(employeeRequest.birthDate()))
                .phone(employeeRequest.phone())
                .address(employeeRequest.address())
                .office(officeRepository.findById(employeeRequest.officeId()).orElse(null))
                .profileImage(image)
                .build();
        employee.setCreatedBy("admin");
        employeeRepository.save(employee);

        return ResponseEntity.ok().body(new ApiResponse("Employee saved successfully", employeeRequest));
    }

    public ResponseEntity<ApiResponse> getEmployee(UUID id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            return ResponseEntity.badRequest().body(new ApiResponse("Employee not found", id));
        }

        return ResponseEntity.ok().body(new ApiResponse("Employee found", new EmployeeResponse(employee)));
    }

    public ResponseEntity<ApiResponse> getEmployees() {
        return ResponseEntity.ok().body(new ApiResponse("Employees found", employeeRepository.findAll().stream().map(EmployeeResponse::new).toList()));
    }

    public UserDetailsService userDetailsService() {
        return username -> employeeRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public Employee getAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return employeeRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
