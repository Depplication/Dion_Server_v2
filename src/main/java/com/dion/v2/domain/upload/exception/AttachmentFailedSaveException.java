package com.dion.v2.domain.upload.exception;

import com.dion.v2.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class AttachmentFailedSaveException extends BusinessException {

    public static final AttachmentFailedSaveException EXCEPTION = new AttachmentFailedSaveException();

    private AttachmentFailedSaveException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "파일 저장에 실패하였습니다");
    }
}
