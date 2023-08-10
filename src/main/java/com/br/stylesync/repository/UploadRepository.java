package com.br.stylesync.repository;

import com.br.stylesync.model.Upload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadRepository extends JpaRepository<Upload, Long> {
}
