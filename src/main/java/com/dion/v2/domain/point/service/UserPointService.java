package com.dion.v2.domain.point.service;

import com.dion.v2.domain.point.presentation.dto.response.PointResponse;

public interface UserPointService {

    void savePoint(Long adId);

    PointResponse getPoint();

}
