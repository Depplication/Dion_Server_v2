package com.dion.v2.domain.owner.exception;

import com.dion.v2.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class OwnerAlreadyExistsException extends BusinessException {

    public static final OwnerAlreadyExistsException EXCEPTION = new OwnerAlreadyExistsException();

    private OwnerAlreadyExistsException() {
        super(HttpStatus.CONFLICT, "업주가 이미 존재합니다");
    }
}
