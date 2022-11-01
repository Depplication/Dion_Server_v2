package com.dion.v2.domain.advertising.exception;

import com.dion.v2.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class AdWrongException extends BusinessException {

    public static final AdWrongException EXCEPTION = new AdWrongException();

    private AdWrongException() {
        super(HttpStatus.FORBIDDEN, "접근 권한이 없는 게시물입니다");
    }
}
