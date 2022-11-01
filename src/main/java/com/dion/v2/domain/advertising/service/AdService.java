package com.dion.v2.domain.advertising.service;

import com.dion.v2.domain.advertising.presentation.dto.request.CreateAdRequest;

public interface AdService {

    Long createAd(CreateAdRequest data);

    void deleteAd(Long adId);
}
