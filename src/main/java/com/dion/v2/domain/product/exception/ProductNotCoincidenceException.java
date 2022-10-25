package com.dion.v2.domain.product.exception;

import com.dion.v2.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class ProductNotCoincidenceException extends BusinessException {

    public ProductNotCoincidenceException(Long id) {
        super(HttpStatus.BAD_REQUEST, String.format("일치하는 상품이 없습니다 : %d", id));
    }
}
