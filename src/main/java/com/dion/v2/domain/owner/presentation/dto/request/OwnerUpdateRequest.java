package com.dion.v2.domain.owner.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class OwnerUpdateRequest {

    private String ownerName;
    private String ownerNumber;
    private String storeName;
    private String addressLongitude;
    private String addressLatitude;

}
