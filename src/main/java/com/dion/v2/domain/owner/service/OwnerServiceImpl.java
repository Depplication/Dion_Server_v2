package com.dion.v2.domain.owner.service;

import com.dion.v2.domain.owner.entity.Account;
import com.dion.v2.domain.owner.entity.Owner;
import com.dion.v2.domain.owner.exception.OwnerAlreadyExistsException;
import com.dion.v2.domain.owner.exception.OwnerNotFoundException;
import com.dion.v2.domain.owner.facade.OwnerFacade;
import com.dion.v2.domain.owner.presentation.dto.request.OwnerSignInRequest;
import com.dion.v2.domain.owner.presentation.dto.request.OwnerSignUpRequest;
import com.dion.v2.domain.owner.presentation.dto.request.OwnerUpdateRequest;
import com.dion.v2.domain.owner.presentation.dto.response.OwnerResponse;
import com.dion.v2.domain.owner.presentation.dto.response.OwnerTokenResponse;
import com.dion.v2.domain.owner.repository.OwnerRepository;
import com.dion.v2.global.exception.PasswordWrongException;
import com.dion.v2.global.security.JwtProvider;
import com.dion.v2.global.utils.OwnerUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService{

    private final OwnerRepository ownerRepository;
    private final OwnerFacade ownerFacade;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final OwnerUtils ownerUtils;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void ownerSignUp(OwnerSignUpRequest request) {
        ownerRepository.findByOwnerId(request.getId())
                .ifPresent(m -> {
                    throw OwnerAlreadyExistsException.EXCEPTION;
                });

        Owner owner = Owner.builder()
                .ownerId(request.getId())
                .password(passwordEncoder.encode(request.getPassword()))
                .ownerName(request.getOwnerName())
                .storeName(request.getStoreName())
                .ownerNumber(request.getOwnerNumber())
                .addressLatitude(request.getAddress()[0])
                .addressLongitude(request.getAddress()[1])
                .accountList(new ArrayList<>())
                .build();
        owner = ownerRepository.save(owner);

        for (String account : request.getAccount()) {
            owner.addAccount(Account.builder()
                    .accountNumber(account)
                    .build());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OwnerTokenResponse ownerSignIn(OwnerSignInRequest request) {
        Owner owner = ownerRepository.findByOwnerId(request.getId())
                .orElseThrow(() -> {throw OwnerNotFoundException.EXCEPTION;});

        if(passwordEncoder.matches(request.getPw(), owner.getPassword())) {
            String token = jwtProvider.generateAccessToken(owner.getId());

            return OwnerTokenResponse.builder()
                    .ownerData(ownerUtils.getOwnerResponse(owner))
                    .token(token)
                    .build();
        } else {
            throw PasswordWrongException.EXCEPTION;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OwnerResponse ownerUpdate(OwnerUpdateRequest request) {
        Owner owner = ownerFacade.queryOwner(true);

        owner.updateOwner(
                request.getOwnerName(), request.getOwnerNumber(),
                request.getStoreName(), request.getAddressLongitude(),
                request.getAddressLatitude()
        );
        owner = ownerRepository.save(owner);

        return ownerUtils.getOwnerResponse(owner);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void ownerDelete() {
        Owner owner = ownerFacade.queryOwner(true);

        owner.getAccountList().stream()
                .map(it -> owner.getAccountList().remove(it))
                .close();
        ownerRepository.delete(owner);
    }

    @Override
    @Transactional(readOnly = true)
    public OwnerResponse getOwner() {
        Owner owner = ownerFacade.queryOwner(true);

        return ownerUtils.getOwnerResponse(owner);
    }
}
