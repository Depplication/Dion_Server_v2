package com.dion.v2.global.utils;

import com.dion.v2.domain.auth.entity.User;
import com.dion.v2.domain.auth.presentation.dto.response.AccountResponse;
import com.dion.v2.domain.auth.presentation.dto.response.AddressResponse;
import com.dion.v2.domain.auth.presentation.dto.response.UserResponse;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.stream.Collectors;

public class UserUtils {

    public UserResponse getUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .userName(user.getUserName())
                .userNumber(user.getUserNumber())
                .userAddress(new AddressResponse(user.getAddressLatitude(), user.getAddressLongitude()))
                .createdDate(
                        new SimpleDateFormat("yyyy-MM-dd")
                                .format(Date.from(
                                        user.getCreatedDate().atZone(ZoneId.systemDefault()).toInstant()
                                ))
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
