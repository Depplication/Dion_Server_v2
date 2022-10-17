package com.dion.v2.global.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @AllArgsConstructor
@Builder
public class AddressResponse {

    private String latitude;

    private String Longitude;

}
