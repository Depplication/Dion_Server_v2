package com.dion.v2.domain.auth.presentation.dto.response;

import com.dion.v2.global.response.AccountResponse;
import com.dion.v2.global.response.AddressResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter @AllArgsConstructor
@Builder
public class UserResponse {

    private Long id;
    private String userId;
    private String userName;
    private String userNumber;
    private String createdDate;
    private AddressResponse userAddress;
    private List<AccountResponse> accountList;

}
