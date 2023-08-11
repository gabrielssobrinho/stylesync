package com.br.stylesync.service;

import com.br.stylesync.dto.ApiResponse;
import com.br.stylesync.dto.OfficeRequest;
import com.br.stylesync.model.Office;
import com.br.stylesync.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OfficeService {

    @Autowired
    private OfficeRepository officeRepository;

    public ResponseEntity<ApiResponse> saveOffice(OfficeRequest officeRequest) {
        if (officeRepository.existsByName(officeRequest.name())) {
            return ResponseEntity.badRequest().body(new ApiResponse("Office already exists", officeRequest.name()));
        }

        Office office = Office.builder()
                .name(officeRequest.name())
                .description(officeRequest.description())
                .build();
        office.setCreatedBy("admin");
        officeRepository.save(office);

        return ResponseEntity.ok().body(new ApiResponse("Office saved successfully", officeRequest));
    }

    public ResponseEntity<ApiResponse> getAllOffices() {
        return ResponseEntity.ok().body(new ApiResponse("All Offices", officeRepository.findAll()));
    }
}
