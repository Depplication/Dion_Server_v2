package com.dion.v2.domain.auth.presentation;

import com.dion.v2.domain.auth.presentation.dto.request.UserSignInRequest;
import com.dion.v2.domain.auth.presentation.dto.request.UserSignUpRequest;
import com.dion.v2.domain.auth.presentation.dto.request.UserUpdateRequest;
import com.dion.v2.domain.auth.presentation.dto.response.UserResponse;
import com.dion.v2.domain.auth.presentation.dto.response.UserTokenResponse;
import com.dion.v2.domain.auth.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "유저")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @ApiOperation("유저 회원가입")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sign-up")
    public void userSignUp(
            @RequestBody @Valid UserSignUpRequest request
    ) {
        authService.userSignUp(request);
    }

    @ApiOperation("유저 로그인")
    @PostMapping("/sign-in")
    public UserTokenResponse userSignIn(
            @RequestBody UserSignInRequest request
    ) {
        return authService.userSignIn(request);
    }

    @ApiOperation("유저 정보 가져오기")
    @GetMapping("/")
    public UserResponse getUser() {
        return authService.getUser();
    }

    @ApiOperation("유저 정보 수정")
    @PatchMapping("/")
    public UserResponse userUpdate(
            @RequestBody UserUpdateRequest request
    ) {
        return authService.userUpdate(request);
    }

    @ApiOperation("유저 탈퇴")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/")
    public void userDelete() {
        authService.userDelete();
    }

}