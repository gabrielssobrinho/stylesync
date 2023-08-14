package com.br.stylesync.service;

import com.br.stylesync.enums.UploadStatus;
import com.br.stylesync.model.Upload;
import com.br.stylesync.model.csv.ProductCsv;
import com.br.stylesync.repository.UploadRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class UploadService {

    private final UploadRepository uploadRepository;

    public Upload save(File file) {
        Upload upload = new Upload();
        upload.setUploadStatus(UploadStatus.IN_PROCESS);
        upload.setFile(file);
        finalizeImport(upload);
        return this.uploadRepository.save(upload);
    }

    public void finalizeImport(Upload upload) {
        try {
            log.info("Finalize upload - {}", upload);

            FileInputStream fis = new FileInputStream(upload.getFile());
            InputStreamReader reader = new InputStreamReader(fis, StandardCharsets.UTF_8);

            CsvToBean<ProductCsv> csvReader = new CsvToBeanBuilder<ProductCsv>(reader)
                    .withType(ProductCsv.class)
                    .withSeparator(';')
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreQuotations(true)
                    .withIgnoreEmptyLine(true)
                    .build();

            List<ProductCsv> results = csvReader.parse();

            this.processDataByCsv(results, upload);
            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            this.finalizeImportWithError(upload);
        }
        this.uploadRepository.save(upload);
    }

    public void finalizeImportWithError(Upload upload) {
        try {
            upload.setUploadStatus(UploadStatus.ERROR);

        } catch (Exception ex) {
            throw new InvalidParameterException(ex.getMessage(), ex);
        }
    }

    public void processDataByCsv(List<ProductCsv> editedCsv, Upload upload) {
        log.info("testing process data by upload version {}", upload);
        editedCsv.forEach(line -> log.info(line.toString()));
    }
}
