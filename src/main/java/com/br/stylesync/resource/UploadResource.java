package com.br.stylesync.resource;

import com.br.stylesync.service.UploadService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.io.File;

@RestController
@RequestMapping("/v1/upload")
@AllArgsConstructor
@Slf4j
public class UploadResource {
    private final UploadService uploadService;

    @PostMapping
    public DeferredResult<ResponseEntity<Void>> requireApproval(@RequestPart("file") File file) {
        log.info("import starts");
        final DeferredResult<ResponseEntity<Void>> dr = new DeferredResult<>();
        uploadService.save(file);
        dr.setResult(ResponseEntity.ok().build());
        return dr;
    }
}
