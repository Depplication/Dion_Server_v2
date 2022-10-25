package com.dion.v2.domain.advertising.presentation.dto.response;

import com.dion.v2.domain.advertising.type.Category;
import com.dion.v2.domain.product.presentation.dto.response.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter @AllArgsConstructor
@Builder
public class AdResponse {

    private Long adId;

    private String title;

    private String cntent;

    private String explain;

    private String email;

    private String startDate;

    private String endDate;

    private Category category;

    private List<ProductResponse> productList;
}
