package com.dion.v2.domain.owner.exception;

import com.dion.v2.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class OwnerNotFoundException extends BusinessException {

    public static final OwnerNotFoundException EXCEPTION = new OwnerNotFoundException();

    private OwnerNotFoundException() {
        super(HttpStatus.NOT_FOUND, "업주를 찾을 수 없습니다");
    }
}
