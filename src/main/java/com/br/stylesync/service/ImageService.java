package com.br.stylesync.service;

import com.br.stylesync.model.Image;
import com.br.stylesync.repository.ImageRepository;
import com.br.stylesync.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public Image uploadImage(MultipartFile file) throws IOException {
        if(file == null){
            return null;
        }
        return imageRepository.save(Image.builder()
                .type(file.getContentType())
                .image(ImageUtils.compressImage(file.getBytes())).build());
    }

    public Image getImageDetails(UUID id) {

        final Optional<Image> dbImage = imageRepository.findById(id);

        return Image.builder()
                .type(dbImage.get().getType())
                .image(ImageUtils.decompressImage(dbImage.get().getImage())).build();
    }

    public ResponseEntity<byte[]> getImage(UUID id) {

        final Optional<Image> dbImage = imageRepository.findById(id);

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(dbImage.get().getType()))
                .body(ImageUtils.decompressImage(dbImage.get().getImage()));
    }
}
