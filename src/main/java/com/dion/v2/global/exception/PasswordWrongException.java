package com.dion.v2.domain.auth.exception;

import com.dion.v2.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class UserPasswordWrongException extends BusinessException {

    public static final UserPasswordWrongException EXCEPTION = new UserPasswordWrongException();

    private UserPasswordWrongException() {
        super(HttpStatus.BAD_REQUEST, "비밀번호가 다릅니다");
    }
}
