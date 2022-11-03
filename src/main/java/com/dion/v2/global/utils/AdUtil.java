package com.dion.v2.global.utils;

import com.dion.v2.domain.advertising.entity.Advertising;
import com.dion.v2.domain.advertising.presentation.dto.response.AdResponse;
import com.dion.v2.domain.product.entity.Product;
import com.dion.v2.domain.product.presentation.dto.response.ProductResponse;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class AdUtil {

    public static AdResponse getAdResponse(Advertising ad) {
        return AdResponse.builder()
                .adId(ad.getAdId())
                .title(ad.getAdTitle())
                .content(ad.getAdContent())
                .category(ad.getCategory())
                .email(ad.getEmail())
                .explain(ad.getStoreExplain())
                .startDate(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(ad.getStartDate()))
                .endDate(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(ad.getEndDate()))
                .productList(ad.getProductList().stream()
                        .map(AdUtil::getProductResponse)
                        .collect(Collectors.toList()))
                .build();
    }

    public static ProductResponse getProductResponse(Product product) {
        return ProductResponse.builder()
                .productId(product.getProductId())
                .name(product.getProductName())
                .content(product.getContent())
                .price(product.getPrice())
                .build();
    }

}
