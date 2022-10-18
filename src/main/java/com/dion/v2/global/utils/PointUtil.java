package com.dion.v2.global.utils;

import com.dion.v2.domain.point.entity.OwnerPoint;
import com.dion.v2.domain.point.entity.UserPoint;
import com.dion.v2.domain.point.presentation.dto.response.PointResponse;

import java.time.format.DateTimeFormatter;

public class PointUtil {

    public static PointResponse getOwnerPointResponse(OwnerPoint point) {
        return PointResponse.builder()
                .pointId(point.getPointId())
                .point(point.getPoint())
                .lastSaveDate(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(point.getLastSaveDate()))
                .build();
    }

    public static PointResponse getUserPointResponse(UserPoint point) {
        return PointResponse.builder()
                .pointId(point.getPointId())
                .point(point.getPoint())
                .lastSaveDate(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(point.getLastSaveDate()))
                .build();
    }

}
