package com.dion.v2.domain.auth.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class UserSignInRequest {

    private String id;

    private String pw;

}
