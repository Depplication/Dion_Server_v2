package com.dion.v2.domain.upload.service;

import com.dion.v2.domain.upload.entity.ProductAttachment;
import com.dion.v2.domain.upload.exception.AttachmentFailedSaveException;
import com.dion.v2.domain.upload.exception.AttachmentNotFoundException;
import com.dion.v2.domain.upload.repository.ProductAttachmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ProductUploadServiceImpl implements UploadService{

    private final ProductAttachmentRepository productAttachmentRepository;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Long uploadAttachment(MultipartFile file) {
        try {
            ProductAttachment attachment = ProductAttachment.builder()
                    .clientName(file.getName())
                    .content(file.getBytes())
                    .build();
            attachment = productAttachmentRepository.save(attachment);

            return attachment.getProductAttachmentId();
        } catch (IOException ex) {
            throw AttachmentFailedSaveException.EXCEPTION;
        }
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public ResponseEntity<byte[]> getAttachment(Long attachmentId) {
        ProductAttachment attachment = productAttachmentRepository.findById(attachmentId)
                .orElseThrow(() -> AttachmentNotFoundException.EXCEPTION);

        String contentDisposition = String.format("attachment; filename=\"%s\"", attachment.getClientName());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(attachment.getContent());
    }
}
