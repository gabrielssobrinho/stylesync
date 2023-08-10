package com.br.stylesync.resource;

import com.br.stylesync.dto.ApiResponse;
import com.br.stylesync.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@RequestMapping("employee")
@RequiredArgsConstructor
public class EmployeeResource {

    private final EmployeeService employeeService;

    @PostMapping
    public DeferredResult<ResponseEntity<ApiResponse>> saveEmployee(@RequestBody EmployeeRequest employeeRequest){
        final DeferredResult<ResponseEntity<ApiResponse>> deferredResult = new DeferredResult<>();
        deferredResult.setResult(this.employeeService.saveEmployee());
        return deferredResult;
    }

}
