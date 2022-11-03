package com.dion.v2.global.security;

import com.dion.v2.domain.auth.entity.User;
import com.dion.v2.domain.owner.entity.Owner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public class UserToken extends UsernamePasswordAuthenticationToken {

    public UserToken(User user) {
        super(user, null, null);
    }

    public UserToken(Owner owner) {
        super(owner, null, null);
    }

}
