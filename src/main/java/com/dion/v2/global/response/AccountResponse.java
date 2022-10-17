package com.dion.v2.global.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @AllArgsConstructor
@Builder
public class AccountResponse {

    private Long accountId;

    private String accountNumber;

}
