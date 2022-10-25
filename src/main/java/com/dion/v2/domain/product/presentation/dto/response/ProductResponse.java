package com.dion.v2.domain.product.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @AllArgsConstructor
@Builder
public class ProductResponse {

    private Long productId;

    private String name;

    private String content;

    private Long price;

}
