package com.dion.v2.domain.advertising.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter @AllArgsConstructor
@Builder
public class AdListResponse {

    private List<AdResponse> list;

}
