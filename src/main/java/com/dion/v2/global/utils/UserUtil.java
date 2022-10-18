package com.dion.v2.global.utils;

import com.dion.v2.domain.auth.entity.User;
import com.dion.v2.global.response.AccountResponse;
import com.dion.v2.global.response.AddressResponse;
import com.dion.v2.domain.auth.presentation.dto.response.UserResponse;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class UserUtil {

    public static UserResponse getUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .userName(user.getUserName())
                .userNumber(user.getUserNumber())
                .userAddress(new AddressResponse(user.getAddressLatitude(), user.getAddressLongitude()))
                .createdDate(
                        DateTimeFormatter.ofPattern("yyyy-MM-dd").format(user.getCreatedDate())
                )
                .accountList(user.getAccountList().stream().map(it ->
                        AccountResponse.builder()
                                .accountId(it.getAccountId())
                                .accountNumber(it.getAccountNumber())
                                .build()
                ).collect(Collectors.toList()))
                .build();
    }

}
