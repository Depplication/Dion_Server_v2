package com.dion.v2.domain.product.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class CreateProductRequest {

    private String name;
    private String content;
    private Long price;

}
