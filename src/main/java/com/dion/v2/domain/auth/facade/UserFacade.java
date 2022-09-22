package com.dion.v2.domain.auth.facade;

import com.dion.v2.domain.auth.entity.User;
import com.dion.v2.domain.auth.exception.UserNotFoundException;
import com.dion.v2.domain.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;

    public User queryCurrentUser() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userRepository.findById(principal.getId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public User queryUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

}
