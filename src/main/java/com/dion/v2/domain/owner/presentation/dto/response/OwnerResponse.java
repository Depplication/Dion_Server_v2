package com.dion.v2.domain.owner.presentation.dto.response;

import com.dion.v2.global.response.AccountResponse;
import com.dion.v2.global.response.AddressResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter @AllArgsConstructor
@Builder
public class OwnerResponse {

    private Long id;
    private String ownerId;
    private String ownerName;
    private String storeName;
    private String ownerNumber;
    private String createdDate;
    private AddressResponse ownerAddress;
    private List<AccountResponse> accountList;

}
