package com.dion.v2.domain.owner.presentation.dto.request;

import com.dion.v2.global.annotation.password.Password;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class OwnerSignUpRequest {

    private String id;
    @Password
    private String password;
    private String ownerName;
    private String ownerNumber;
    private String storeNumber;
    private String storeName;
    private String[] address;
    private String[] account;

}
