package com.dion.v2.domain.advertising.service;

import com.dion.v2.domain.advertising.presentation.dto.request.CreateAdRequest;
import com.dion.v2.domain.advertising.presentation.dto.response.AdListResponse;
import com.dion.v2.domain.advertising.presentation.dto.response.AdResponse;
import com.dion.v2.domain.advertising.type.Category;

public interface AdService {

    Long createAd(CreateAdRequest data);

    AdResponse getAd(Long adId);

    AdListResponse getAdByTitle(String title);

    AdListResponse getAdByCategory(Category category);

    void deleteAd(Long adId);
}
