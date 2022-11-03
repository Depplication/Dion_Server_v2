package com.dion.v2.domain.upload.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    Long uploadAttachment(MultipartFile file);

    ResponseEntity<byte[]> getAttachment(Long attachmentId);

}
