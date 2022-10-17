package com.dion.v2.domain.auth.service;

import com.dion.v2.domain.auth.presentation.dto.request.UserSignInRequest;
import com.dion.v2.domain.auth.presentation.dto.request.UserSignUpRequest;
import com.dion.v2.domain.auth.presentation.dto.request.UserUpdateRequest;
import com.dion.v2.domain.auth.presentation.dto.response.UserResponse;
import com.dion.v2.domain.auth.presentation.dto.response.UserTokenResponse;

public interface AuthService {

    void userSignUp(UserSignUpRequest request);

    UserTokenResponse userSignIn(UserSignInRequest request);

    UserResponse userUpdate(UserUpdateRequest request);

    void userDelete();

    UserResponse getUser();
}
