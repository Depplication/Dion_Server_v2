package com.dion.v2.domain.upload.exception;

import com.dion.v2.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class AttachmentNotFoundException extends BusinessException {

    public static final AttachmentNotFoundException EXCEPTION = new AttachmentNotFoundException();

    private AttachmentNotFoundException() {
        super(HttpStatus.NOT_FOUND, "파일을 찾을 수 없습니다");
    }
}
