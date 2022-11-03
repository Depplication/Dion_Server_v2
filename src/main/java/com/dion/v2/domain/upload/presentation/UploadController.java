package com.dion.v2.domain.upload.presentation;

import com.dion.v2.domain.upload.repository.AdvertisingAttachmentRepository;
import com.dion.v2.domain.upload.repository.ProductAttachmentRepository;
import com.dion.v2.domain.upload.service.AdUploadServiceImpl;
import com.dion.v2.domain.upload.service.ProductUploadServiceImpl;
import com.dion.v2.domain.upload.service.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "첨부파일")
@RestController
@RequiredArgsConstructor
@RequestMapping("/upload")
public class UploadController {

    private final AdUploadServiceImpl adUploadService;

    private final ProductUploadServiceImpl productUploadService;

    @ApiOperation("상품 첨부파일을 업로드합니다")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/product/attachments")
    public Long uploadProductAttachment(@RequestParam("preview") MultipartFile file) {
        return productUploadService.uploadAttachment(file);
    }

    @ApiOperation("상품 첨부파일을 가져옵니다")
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(value = "/product/attachments/{product-attachment-id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> getProductAttachment(@PathVariable("product-attachment-id") Long id) {
        return productUploadService.getAttachment(id);
    }

    @ApiOperation("광고 첨부파일을 업로드합니다")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/ad/attachments")
    public Long uploadAdAttachment(@RequestParam("preview") MultipartFile file) {
        return adUploadService.uploadAttachment(file);
    }

    @ApiOperation("광고 첨부파일을 가져옵니다")
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping(value = "/ad/attachments/{ad-attachment-id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> getAdAttachment(@PathVariable("ad-attachment-id") Long id) {
        return adUploadService.getAttachment(id);
    }

}
