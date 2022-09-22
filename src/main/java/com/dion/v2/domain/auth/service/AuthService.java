package com.dion.v2.domain.auth.service;

import com.dion.v2.domain.auth.presentation.dto.request.UserSignInRequest;
import com.dion.v2.domain.auth.presentation.dto.request.UserSignUpRequest;
import com.dion.v2.domain.auth.presentation.dto.response.UserTokenResponse;

public interface AuthService {

    Long userSignUp(UserSignUpRequest request);

    UserTokenResponse userSignIn(UserSignInRequest request);

}
