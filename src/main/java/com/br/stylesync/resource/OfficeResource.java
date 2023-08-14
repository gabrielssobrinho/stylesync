package com.br.stylesync.resource;

import com.br.stylesync.dto.response.ApiResponse;
import com.br.stylesync.dto.request.OfficeRequest;
import com.br.stylesync.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@RequestMapping("office")
public class OfficeResource {

    @Autowired
    private OfficeService officeService;

    @PostMapping
    public DeferredResult<ResponseEntity<ApiResponse>> saveOffice(@RequestBody OfficeRequest officeRequest) {
        final DeferredResult<ResponseEntity<ApiResponse>> deferredResult = new DeferredResult<>();
        deferredResult.setResult(this.officeService.saveOffice(officeRequest));
        return deferredResult;
    }

    @GetMapping
    public DeferredResult<ResponseEntity<ApiResponse>> getAllOffices() {
        final DeferredResult<ResponseEntity<ApiResponse>> deferredResult = new DeferredResult<>();
        deferredResult.setResult(this.officeService.getAllOffices());
        return deferredResult;
    }
}
