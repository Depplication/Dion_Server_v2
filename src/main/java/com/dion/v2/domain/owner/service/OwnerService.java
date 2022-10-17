package com.dion.v2.domain.owner.service;

import com.dion.v2.domain.owner.presentation.dto.request.OwnerSignInRequest;
import com.dion.v2.domain.owner.presentation.dto.request.OwnerSignUpRequest;
import com.dion.v2.domain.owner.presentation.dto.request.OwnerUpdateRequest;
import com.dion.v2.domain.owner.presentation.dto.response.OwnerResponse;
import com.dion.v2.domain.owner.presentation.dto.response.OwnerTokenResponse;

public interface OwnerService {

    void ownerSignUp(OwnerSignUpRequest request);

    OwnerTokenResponse ownerSignIn(OwnerSignInRequest request);

    OwnerResponse ownerUpdate(OwnerUpdateRequest request);

    void ownerDelete();

    OwnerResponse getOwner();

}
