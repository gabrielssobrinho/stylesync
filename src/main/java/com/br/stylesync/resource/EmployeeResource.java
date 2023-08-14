package com.br.stylesync.resource;

import com.br.stylesync.dto.response.ApiResponse;
import com.br.stylesync.dto.request.EmployeeRequest;
import com.br.stylesync.dto.UpdateEmployeeDto;
import com.br.stylesync.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.UUID;

@RestController
@RequestMapping("employee")
@Slf4j
public class EmployeeResource {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<ApiResponse> saveEmployee(@RequestBody EmployeeRequest employeeRequest) throws ParseException, IOException {
        log.info("saving employee");
        return this.employeeService.saveEmployee(employeeRequest);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getEmployees() {
        log.info("getting employees");
        return this.employeeService.getEmployees();
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse> getEmployee(@PathVariable UUID id) {
        log.info("getting employee by id: {}", id);
        return this.employeeService.getEmployee(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> updateEmployee(@PathVariable UUID id, @RequestBody UpdateEmployeeDto employeeRequest) throws ParseException, IOException {
        log.info("updating employee by id: {}", id);
        return this.employeeService.updateEmployee(id, employeeRequest);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable UUID id) {
        log.info("deleting employee by id: {}", id);
        return this.employeeService.deleteEmployee(id);
    }

}
