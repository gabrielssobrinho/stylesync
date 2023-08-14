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
    public ResponseEntity<ApiResponse> saveOffice(@RequestBody OfficeRequest officeRequest) {
        return officeService.saveOffice(officeRequest);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllOffices() {
        return officeService.getAllOffices();
    }
}
