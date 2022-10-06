package com.dion.v2.domain.auth.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class UserUpdateRequest {

    private String userName;
    private String userNumber;
    private String addressLongitude;
    private String addressLatitude;

}
