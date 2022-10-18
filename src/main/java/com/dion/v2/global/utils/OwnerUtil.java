package com.dion.v2.global.utils;

import com.dion.v2.domain.owner.entity.Owner;
import com.dion.v2.domain.owner.presentation.dto.response.OwnerResponse;
import com.dion.v2.global.response.AccountResponse;
import com.dion.v2.global.response.AddressResponse;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class OwnerUtil {

    public static OwnerResponse getOwnerResponse(Owner owner) {
        return OwnerResponse.builder()
                .id(owner.getId())
                .ownerId(owner.getOwnerId())
                .ownerName(owner.getOwnerName())
                .storeName(owner.getStoreName())
                .ownerNumber(owner.getOwnerNumber())
                .ownerAddress(new AddressResponse(owner.getAddressLatitude(), owner.getAddressLongitude()))
                .createdDate(
                        DateTimeFormatter.ofPattern("yyyy-MM-dd").format(owner.getCreatedDate())
                )
                .accountList(owner.getAccountList().stream().map(it ->
                        AccountResponse.builder()
                                .accountId(it.getAccountId())
                                .accountNumber(it.getAccountNumber())
                                .build()
                ).collect(Collectors.toList()))
                .build();
    }

}
