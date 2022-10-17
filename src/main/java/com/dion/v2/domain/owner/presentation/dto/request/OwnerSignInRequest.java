package com.dion.v2.domain.owner.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class OwnerSignInRequest {

    private String id;
    private String pw;

}
