package com.dion.v2.domain.auth.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @AllArgsConstructor
@Builder
public class UserTokenResponse {

    private String token;
    private UserResponse userData;

}
