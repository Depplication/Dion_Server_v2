package com.dion.v2.domain.upload.service;

import com.dion.v2.domain.upload.entity.AdvertisingAttachment;
import com.dion.v2.domain.upload.exception.AttachmentFailedSaveException;
import com.dion.v2.domain.upload.exception.AttachmentNotFoundException;
import com.dion.v2.domain.upload.repository.AdvertisingAttachmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AdUploadServiceImpl implements UploadService {

    private final AdvertisingAttachmentRepository adAttachmentRepository;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Long uploadAttachment(MultipartFile file) {
        try {
            AdvertisingAttachment attachment = AdvertisingAttachment.builder()
                    .clientName(file.getName())
                    .content(file.getBytes())
                    .build();
            attachment = adAttachmentRepository.save(attachment);

            return attachment.getAdvertisingAttachmentId();
        } catch (IOException ex) {
            throw AttachmentFailedSaveException.EXCEPTION;
        }
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public ResponseEntity<byte[]> getAttachment(Long attachmentId) {
        AdvertisingAttachment attachment = adAttachmentRepository.findById(attachmentId)
                .orElseThrow(() -> AttachmentNotFoundException.EXCEPTION);

        String contentDisposition = String.format("attachment; filename=\"%s\"", attachment.getClientName());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(attachment.getContent());
    }
}
