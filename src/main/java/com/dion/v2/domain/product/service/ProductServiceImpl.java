package com.dion.v2.domain.product.service;

import com.dion.v2.domain.owner.entity.Owner;
import com.dion.v2.domain.owner.facade.OwnerFacade;
import com.dion.v2.domain.product.entity.Product;
import com.dion.v2.domain.product.exception.ProductNotFoundException;
import com.dion.v2.domain.product.presentation.dto.request.CreateProductRequest;
import com.dion.v2.domain.product.presentation.dto.response.ProductResponse;
import com.dion.v2.domain.product.repository.ProductRepository;
import com.dion.v2.global.utils.AdUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private OwnerFacade ownerFacade;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createProduct(CreateProductRequest data) {
        Owner author = ownerFacade.queryOwner(true);

        Product product = Product.builder()
                .productName(data.getName())
                .content(data.getContent())
                .price(data.getPrice())
                .build();
        product.setAuthor(author);

        return productRepository.save(product).getProductId();
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public ProductResponse getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> {throw ProductNotFoundException.EXCEPTION;});

        return AdUtil.getProductResponse(product);
    }


}
