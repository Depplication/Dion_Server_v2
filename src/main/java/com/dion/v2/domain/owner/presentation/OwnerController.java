package com.dion.v2.domain.owner.presentation;

import com.dion.v2.domain.owner.presentation.dto.request.OwnerSignInRequest;
import com.dion.v2.domain.owner.presentation.dto.request.OwnerSignUpRequest;
import com.dion.v2.domain.owner.presentation.dto.request.OwnerUpdateRequest;
import com.dion.v2.domain.owner.presentation.dto.response.OwnerResponse;
import com.dion.v2.domain.owner.presentation.dto.response.OwnerTokenResponse;
import com.dion.v2.domain.owner.service.OwnerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api("업주")
@RestController
@RequiredArgsConstructor
@RequestMapping("/owner")
public class OwnerController {

    private OwnerService ownerService;

    @ApiOperation("업주 회원가입")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sign-up")
    public void ownerSignUp(
            @RequestBody @Valid OwnerSignUpRequest request
    ) {
        ownerService.ownerSignUp(request);
    }

    @ApiOperation("엄주 로그인")
    @PostMapping("/sign-in")
    public OwnerTokenResponse ownerSignIn(
            @RequestBody OwnerSignInRequest request
    ) {
        return ownerService.ownerSignIn(request);
    }

    @ApiOperation("업주 정보 가져오기")
    @GetMapping("/")
    public OwnerResponse getOwner() {
        return ownerService.getOwner();
    }

    @ApiOperation("업주 정보 수정")
    @PatchMapping("/")
    public OwnerResponse ownerUpdate(
        @RequestBody OwnerUpdateRequest request
    ) {
        return ownerService.ownerUpdate(request);
    }

    @ApiOperation("업주 탈퇴")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/")
    public void ownerDelete() {
        ownerService.ownerDelete();
    }

}
