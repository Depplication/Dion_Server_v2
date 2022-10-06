package com.dion.v2.domain.auth.service;

import com.dion.v2.domain.auth.entity.Account;
import com.dion.v2.domain.auth.entity.User;
import com.dion.v2.domain.auth.exception.UserAlreadyExistsException;
import com.dion.v2.domain.auth.exception.UserNotFoundException;
import com.dion.v2.domain.auth.exception.UserPasswordWrongException;
import com.dion.v2.domain.auth.facade.UserFacade;
import com.dion.v2.domain.auth.presentation.dto.request.UserSignInRequest;
import com.dion.v2.domain.auth.presentation.dto.request.UserSignUpRequest;
import com.dion.v2.domain.auth.presentation.dto.request.UserUpdateRequest;
import com.dion.v2.domain.auth.presentation.dto.response.AccountResponse;
import com.dion.v2.domain.auth.presentation.dto.response.AddressResponse;
import com.dion.v2.domain.auth.presentation.dto.response.UserResponse;
import com.dion.v2.domain.auth.presentation.dto.response.UserTokenResponse;
import com.dion.v2.domain.auth.repository.UserRepository;
import com.dion.v2.global.security.JwtProvider;
import com.dion.v2.global.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final UserFacade userFacade;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final UserUtils userUtils;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long userSignUp(UserSignUpRequest request) {
        userRepository.findByUserId(request.getId())
                .ifPresent(m -> {
                    throw UserAlreadyExistsException.EXCEPTION;
                });

        User user = User.builder()
                .userId(request.getId())
                .password(passwordEncoder.encode(request.getPassword()))
                .userName(request.getName())
                .userNumber(request.getNumber())
                .addressLatitude(request.getAddress()[0])
                .addressLongitude(request.getAddress()[1])
                .accountList(new ArrayList<>())
                .build();
        user = userRepository.save(user);
        for(String account : request.getAccount()) {
            user.addAccount(Account.builder()
                    .accountNumber(account)
                    .build());
        }

        return user.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserTokenResponse userSignIn(UserSignInRequest request) {
        User user = userRepository.findByUserId(request.getId())
                .orElseThrow(() -> {throw UserNotFoundException.EXCEPTION;});

        if(passwordEncoder.matches(request.getPw(), user.getPassword())) {
            String token = jwtProvider.generateAccessToken(user.getId());

            return UserTokenResponse.builder()
                    .userData(userUtils.getUserResponse(user))
                    .token(token)
                    .build();
        } else {
            throw UserPasswordWrongException.EXCEPTION;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserResponse userUpdate(UserUpdateRequest request) {
        User user = userFacade.queryUser(true);

        user = userRepository.save(user.updateUser(
                request.getUserName(), request.getUserNumber(),
                request.getAddressLongitude(), request.getAddressLatitude()
        ));

        return userUtils.getUserResponse(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void userDelete() {
        User user = userFacade.queryUser(true);

        user.getAccountList().stream()
                .map(it -> user.getAccountList().remove(it))
                .close();
        userRepository.delete(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUser() {
        User user = userFacade.queryUser(true);

        return userUtils.getUserResponse(user);
    }


}
