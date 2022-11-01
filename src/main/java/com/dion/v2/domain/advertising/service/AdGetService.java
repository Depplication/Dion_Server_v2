package com.dion.v2.domain.advertising.service;

import com.dion.v2.domain.advertising.presentation.dto.response.AdListResponse;
import com.dion.v2.domain.advertising.presentation.dto.response.AdResponse;
import com.dion.v2.domain.advertising.type.Category;
import org.springframework.data.domain.Pageable;

public interface AdGetService {

    AdResponse getAd(Long adId);

    AdListResponse getList(Pageable pageable);

    AdListResponse getAdByTitle(Pageable pageable, String title);

    AdListResponse getAdByCategory(Pageable pageable, Category category);

}
