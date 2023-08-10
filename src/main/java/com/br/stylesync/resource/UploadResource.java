package com.br.stylesync.resource;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1/upload")
@AllArgsConstructor
@Slf4j
public class UploadResource {

    @PostMapping
    public DeferredResult<ResponseEntity<Void>> requireApproval(@RequestPart("file") MultipartFile file,
                                                                  @RequestPart String justification) throws Exception {
        log.info("import starts");
        final DeferredResult<ResponseEntity<Void>> dr = new DeferredResult<>();
        CMDataVersion cmDataVersion = cmDataVersionService.createByImport(file, justification);
        dr.setResult(ResponseEntity.ok().build());
        return dr;
    }
}
