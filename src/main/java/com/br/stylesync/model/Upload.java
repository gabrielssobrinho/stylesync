package com.br.stylesync.model;

import com.br.stylesync.enums.UploadStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Entity
@Getter
@Setter
@Table(name = "upload")
@ToString
public class Upload extends AuditEntity{

    @Column(name = "upload_status")
    private UploadStatus uploadStatus;

    @Column(name = "file", nullable = false)
    private File file;

}
