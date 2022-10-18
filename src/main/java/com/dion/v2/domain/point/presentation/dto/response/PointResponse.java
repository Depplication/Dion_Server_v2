package com.dion.v2.domain.point.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @AllArgsConstructor
@Builder
public class PointResponse {

    private Long pointId;
    private Long point;
    private String lastSaveDate;

}
