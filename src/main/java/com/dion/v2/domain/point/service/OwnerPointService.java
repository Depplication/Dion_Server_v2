package com.dion.v2.domain.point.service;

import com.dion.v2.domain.point.presentation.dto.request.SavePointRequest;
import com.dion.v2.domain.point.presentation.dto.response.PointResponse;

public interface OwnerPointService {

    void savePoint(SavePointRequest request);

    PointResponse getPoint();

}
