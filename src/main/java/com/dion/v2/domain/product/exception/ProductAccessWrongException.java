package com.dion.v2.domain.product.exception;

import com.dion.v2.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class ProductAccessWrongException extends BusinessException {

    public static final ProductAccessWrongException EXCEPTION = new ProductAccessWrongException();

    private ProductAccessWrongException() {
        super(HttpStatus.FORBIDDEN, "접근 권한이 없는 상품");
    }
}
