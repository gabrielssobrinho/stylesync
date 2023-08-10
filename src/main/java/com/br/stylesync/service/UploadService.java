package com.br.stylesync.service;

import com.br.stylesync.enums.UploadStatus;
import com.br.stylesync.model.Upload;
import com.br.stylesync.model.csv.ProductCsv;
import com.br.stylesync.repository.UploadRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class UploadService {

    private final UploadRepository uploadRepository;

    public Upload save(File file) throws Exception {
        Upload upload = new Upload();
        upload.setUploadStatus(UploadStatus.IN_PROCESS);
        upload.setFile(file);
        finalizeImport(upload);
        return this.uploadRepository.save(upload);
    }

    public void finalizeImport(Upload upload) {
        try {
            log.info("Finalize upload - {}", upload);

            //CsvToBean<ProductCsv> csvReader = new CsvToBeanBuilder<ProductCsv>(upload.getFile())
            //        .withType(ProductCsv.class)
            //        .withSeparator(';')
            //        .withIgnoreLeadingWhiteSpace(true)
            //        .withIgnoreQuotations(true)
            //        .withIgnoreEmptyLine(true)
            //        .build();

            //List<ProductCsv> results = csvReader.parse();

            //boolean finalizeWithSuccess = this.createCmDataByCsv(results, cmDataVersion);
            //reader.close();

            //if (finalizeWithSuccess) {
            //    cmDataVersion.setUploadStatus(UploadStatus.SUCCESS);
            //    log.info("Building complete - {}", cmDataVersion.getStatus());
            //}

        } catch (Exception ex) {
            ex.printStackTrace();
            //this.finalizeImportWithError(cmDataVersion.getId(), new ArrayList<>());
        }
        //this.cmDataVersionService.save(cmDataVersion);
    }
}
