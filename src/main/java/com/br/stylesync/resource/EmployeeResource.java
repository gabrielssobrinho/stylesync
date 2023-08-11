package com.br.stylesync.resource;

import com.br.stylesync.dto.ApiResponse;
import com.br.stylesync.dto.EmployeeRequest;
import com.br.stylesync.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.io.IOException;
import java.text.ParseException;
import java.util.UUID;

@RestController
@RequestMapping("employee")
public class EmployeeResource {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public DeferredResult<ResponseEntity<ApiResponse>> saveEmployee(@RequestBody EmployeeRequest employeeRequest) throws ParseException, IOException {
        final DeferredResult<ResponseEntity<ApiResponse>> deferredResult = new DeferredResult<>();
        deferredResult.setResult(this.employeeService.saveEmployee(employeeRequest));
        return deferredResult;
    }

    @GetMapping
    public DeferredResult<ResponseEntity<ApiResponse>> getEmployees() {
        final DeferredResult<ResponseEntity<ApiResponse>> deferredResult = new DeferredResult<>();
        deferredResult.setResult(this.employeeService.getEmployees());
        return deferredResult;
    }

    @GetMapping("{id}")
    public DeferredResult<ResponseEntity<ApiResponse>> getEmployee(@PathVariable UUID id) {
        final DeferredResult<ResponseEntity<ApiResponse>> deferredResult = new DeferredResult<>();
        deferredResult.setResult(this.employeeService.getEmployee(id));
        return deferredResult;
    }

}
