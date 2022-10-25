package com.dion.v2.domain.product.service;

import com.dion.v2.domain.product.presentation.dto.request.CreateProductRequest;

public interface ProductService {

    Long createProduct(CreateProductRequest data);

}
