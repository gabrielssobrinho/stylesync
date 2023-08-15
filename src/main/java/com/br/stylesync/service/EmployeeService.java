package com.br.stylesync.service;

import com.br.stylesync.dto.response.ApiResponse;
import com.br.stylesync.dto.request.EmployeeRequest;
import com.br.stylesync.dto.response.EmployeeResponse;
import com.br.stylesync.dto.UpdateEmployeeDto;
import com.br.stylesync.enums.Role;
import com.br.stylesync.model.Employee;
import com.br.stylesync.model.EmployeeActivationToken;
import com.br.stylesync.model.Image;
import com.br.stylesync.repository.EmployeeActivationTokenRepository;
import com.br.stylesync.repository.EmployeeRepository;
import com.br.stylesync.repository.OfficeRepository;
import com.br.stylesync.utils.EmailUtils;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    private EmployeeActivationTokenRepository employeeActivationTokenRepository;

    public ResponseEntity<ApiResponse> saveEmployee(EmployeeRequest employeeRequest) throws ParseException, IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (employeeRepository.existsByEmail(employeeRequest.email())) {
            return ResponseEntity.badRequest().body(new ApiResponse("Email already exists", employeeRequest.email()));
        }

        Image image = null;

        if (employeeRequest.profileImage() != null) {
            image = imageService.uploadImage(employeeRequest.profileImage());
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Employee employee = Employee.builder()
                .name(employeeRequest.name())
                .email(employeeRequest.email())
                .password(passwordEncoder.encode(employeeRequest.password()))
                .birthDate(sdf.parse(employeeRequest.birthDate()))
                .phone(employeeRequest.phone())
                .address(employeeRequest.address())
                .office(officeRepository.findById(employeeRequest.officeId()).orElse(null))
                .profileImage(image)
                .role(Role.EMPLOYEE)
                .build();
        employee.setCreatedBy(Employee.currentUser().getName());
        employeeRepository.save(employee);

        EmailUtils.sendEmail(employee, getActivationLink(employee));
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

    public Employee findByEmail(String userEmail) {
        return employeeRepository.findByEmail(userEmail).orElse(null);
    }

    public ResponseEntity<ApiResponse> updateEmployee(UUID id, UpdateEmployeeDto employeeRequest) throws IOException {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if(employee == null){
            return ResponseEntity.badRequest().body(new ApiResponse("Employee not found", id));
        }
        if(employeeRequest.email() != null && !employeeRequest.email().equals(employee.getEmail())){
            if(employeeRepository.existsByEmail(employeeRequest.email())){
                return ResponseEntity.badRequest().body(new ApiResponse("Email already exists", employeeRequest.email()));
            }
        }
        if(employeeRequest.profileImage() != null){
            Image image = imageService.uploadImage(employeeRequest.profileImage());
            employee.setProfileImage(image);
        }
        if(employeeRequest.password() != null){
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            employee.setPassword(passwordEncoder.encode(employeeRequest.password()));
        }
        if(employeeRequest.officeId() != null && employeeRequest.officeId() != employee.getOffice().getId()){
            employee.setOffice(officeRepository.findById(employeeRequest.officeId()).orElse(null));
        }
        employee.updateUser(employeeRequest);
        employeeRepository.save(employee);
        return ResponseEntity.ok().body(new ApiResponse("Employee updated successfully", new EmployeeResponse(employee)));
    }

    public ResponseEntity<ApiResponse> deleteEmployee(UUID id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if(employee == null){
            return ResponseEntity.badRequest().body(new ApiResponse("Employee not found", id));
        }
        employee.setActive(false);
        employeeRepository.save(employee);
        return ResponseEntity.ok().body(new ApiResponse("Employee deleted successfully", id));
    }

    public String getActivationLink(Employee employee) {
        EmployeeActivationToken employeeActivationToken = new EmployeeActivationToken(employee);
        employeeActivationTokenRepository.save(employeeActivationToken);
        return "http://localhost:8080/employee/activate/" + employeeActivationToken.getId();
    }

    public ResponseEntity<ApiResponse> activateEmployee(UUID token) {
        EmployeeActivationToken employeeActivationToken = employeeActivationTokenRepository.findById(token).orElse(null);
        if (employeeActivationToken == null) {
            return ResponseEntity.badRequest().body(new ApiResponse("Token not found", token));
        }
        Employee employee = employeeActivationToken.getEmployee();
        if(!Employee.currentUser().equals(employee)){
            return ResponseEntity.badRequest().body(new ApiResponse("You can't activate this employee", token));
        }
        if(employeeActivationToken.isExpired()){
            return ResponseEntity.badRequest().body(new ApiResponse("Token expired", token));
        }
        employee.setActive(true);
        employeeRepository.save(employee);
        employeeActivationTokenRepository.delete(employeeActivationToken);
        return ResponseEntity.ok().body(new ApiResponse("Employee activated successfully", new EmployeeResponse(employee)));
    }
}
