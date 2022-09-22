package com.dion.v2.domain.auth.presentation;

import com.dion.v2.domain.auth.presentation.dto.request.UserSignInRequest;
import com.dion.v2.domain.auth.presentation.dto.request.UserSignUpRequest;
import com.dion.v2.domain.auth.presentation.dto.response.UserTokenResponse;
import com.dion.v2.domain.auth.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api("유저")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @ApiOperation("유저 회원가입")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sign-up")
    public Long userSignUp(
            @RequestBody UserSignUpRequest request
    ) {
        return authService.userSignUp(request);
    }

    @ApiOperation("유저 로그인")
    @PostMapping("/sign-in")
    public UserTokenResponse userSignIn(
            @RequestBody UserSignInRequest request
    ) {
        return authService.userSignIn(request);
    }

}