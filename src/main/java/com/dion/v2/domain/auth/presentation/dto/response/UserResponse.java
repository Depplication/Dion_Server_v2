package com.dion.v2.domain.auth.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @AllArgsConstructor
@Builder
public class UserResponse {

    private Long id;

    private String userId;

    private String userName;

    private String userNumber;

    private LocalDateTime createdDate;

    private AddressResponse userAddress;

    private List<AccountResponse> accountList;

}
