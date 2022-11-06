package com.dion.v2.domain.auth.facade;

import com.dion.v2.domain.auth.entity.User;
import com.dion.v2.domain.auth.exception.UserNotFoundException;
import com.dion.v2.domain.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User queryUser(boolean withPersistence) {
        User withoutPersistencce = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        log.info("userid : " + withoutPersistencce.getUserId());
        if(withPersistence) {
            return userRepository.findById(withoutPersistencce.getId())
                    .orElseThrow(() -> UserNotFoundException.EXCEPTION);
        }else {
            return withoutPersistencce;
        }
    }

    public User queryUser() {
        return queryUser(false);
    }

    public User queryUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }



}
