package com.dion.v2.domain.advertising.exception;

import com.dion.v2.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class AdNotFoundException extends BusinessException {

    public static final AdNotFoundException EXCEPTION = new AdNotFoundException();

    private AdNotFoundException() {
        super(HttpStatus.NOT_FOUND, "광고를 찾을 수 없습니다");
    }
}
