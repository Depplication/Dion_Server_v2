package com.dion.v2.domain.auth.service;

import com.dion.v2.domain.auth.entity.UserAccount;
import com.dion.v2.domain.auth.entity.User;
import com.dion.v2.domain.auth.exception.UserAlreadyExistsException;
import com.dion.v2.domain.auth.exception.UserNotFoundException;
import com.dion.v2.domain.point.entity.UserPoint;
import com.dion.v2.global.exception.PasswordWrongException;
import com.dion.v2.domain.auth.facade.UserFacade;
import com.dion.v2.domain.auth.presentation.dto.request.UserSignInRequest;
import com.dion.v2.domain.auth.presentation.dto.request.UserSignUpRequest;
import com.dion.v2.domain.auth.presentation.dto.request.UserUpdateRequest;
import com.dion.v2.domain.auth.presentation.dto.response.UserResponse;
import com.dion.v2.domain.auth.presentation.dto.response.UserTokenResponse;
import com.dion.v2.domain.auth.repository.UserRepository;
import com.dion.v2.global.security.JwtProvider;
import com.dion.v2.global.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final UserFacade userFacade;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void userSignUp(UserSignUpRequest request) {
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
        user.setPoint(new UserPoint(0L));
        user = userRepository.save(user);

        for(String account : request.getAccount()) {
            user.addAccount(UserAccount.builder()
                    .accountNumber(account)
                    .build());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserTokenResponse userSignIn(UserSignInRequest request) {
        User user = userRepository.findByUserId(request.getId())
                .orElseThrow(() -> {throw UserNotFoundException.EXCEPTION;});

        if(passwordEncoder.matches(request.getPw(), user.getPassword())) {
            String token = jwtProvider.generateAccessToken(user.getId());

            return UserTokenResponse.builder()
                    .userData(UserUtil.getUserResponse(user))
                    .token(token)
                    .build();
        } else {
            throw PasswordWrongException.EXCEPTION;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserResponse userUpdate(UserUpdateRequest request) {
        User user = userFacade.queryUser(true);

        user.updateUser(
                request.getUserName(), request.getUserNumber(),
                request.getAddressLongitude(), request.getAddressLatitude());
        user = userRepository.save(user);

        return UserUtil.getUserResponse(user);
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

        log.info("userid : " + user.getUserId());
        return UserUtil.getUserResponse(user);
    }


}
