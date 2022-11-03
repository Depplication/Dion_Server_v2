package com.dion.v2.domain.product.service;

import com.dion.v2.domain.product.presentation.dto.request.CreateProductRequest;
import com.dion.v2.domain.product.presentation.dto.response.ProductResponse;

public interface ProductService {

    Long createProduct(CreateProductRequest data);

    ProductResponse getProduct(Long productId);

}
