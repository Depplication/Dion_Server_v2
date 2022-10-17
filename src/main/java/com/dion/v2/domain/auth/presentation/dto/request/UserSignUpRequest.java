package com.dion.v2.domain.auth.presentation.dto.request;

import com.dion.v2.global.annotation.password.Password;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class UserSignUpRequest {

    private String id;
    @Password
    private String password;
    private String name;
    private String number;
    private String[] address;
    private String[] account;

}
