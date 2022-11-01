package com.dion.v2.domain.product.exception;

import com.dion.v2.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends BusinessException {

    public static final ProductNotFoundException EXCEPTION = new ProductNotFoundException();

    private ProductNotFoundException() {
        super(HttpStatus.NOT_FOUND, "상품을 찾을 수 없습니다");
    }
}
