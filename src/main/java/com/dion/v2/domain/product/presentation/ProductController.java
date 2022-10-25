package com.dion.v2.domain.product.presentation;

import com.dion.v2.domain.product.presentation.dto.request.CreateProductRequest;
import com.dion.v2.domain.product.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api("상품")
@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @ApiOperation("상품 등록하기")
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Long createProduct(
            @RequestBody CreateProductRequest data
    ) {
        return productService.createProduct(data);
    }

}
