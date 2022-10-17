package com.dion.v2.domain.owner.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @AllArgsConstructor
@Builder
public class OwnerTokenResponse {

    private String token;
    private OwnerResponse ownerData;

}
