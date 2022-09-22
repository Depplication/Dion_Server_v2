package com.dion.v2.domain.auth.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class UserSignUpRequest {

    private String id;

    private String password;

    private String name;

    private String number;

    private String[] address;

    private String[] account;

}
