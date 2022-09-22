package com.dion.v2.global.security;

import com.dion.v2.domain.auth.entity.User;
import com.dion.v2.domain.auth.repository.UserRepository;
import com.dion.v2.global.security.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final UserRepository userRepository;
    private JwtProperties jwtProperties;

    public String generateAccessToken(Long userId) {
        return generateToken(userId.toString(), jwtProperties.getAccessExp());
    }

    public String generateRefreshToken(Long userId) {
        return generateToken(userId.toString(), jwtProperties.getRefreshExp());
    }

    @Transactional
    public Authentication authentication(String token) {
        User userDetails = userRepository
                .findById(getTokenSubject(token)).orElseThrow();
        return new UserToken(userDetails);
    }

    public String parseToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith(jwtProperties.getSecretKey()))
            return bearerToken.replace(jwtProperties.getSecretKey(), "");
        return null;
    }

    private Claims getTokenBody(String token) {
        try {
            return Jwts.parser().setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token).getBody();
        } catch (RuntimeException e) {
            throw new RuntimeException("Token body Exception");
        }
    }

    private Long getTokenSubject(String token) {
        return Long.parseLong(getTokenBody(token).getSubject());
    }

    private String generateToken(String id, Long exp) {
       String token = Jwts.builder()
               .setSubject(id)
               .setIssuedAt(new Date())
               .setExpiration(new Date(System.currentTimeMillis() + exp * 1000))
               .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
               .compact();

       log.info("token : " + token);
        return token;
    }

    public LocalDateTime getExpiredTime() {
        return LocalDateTime.now().plusSeconds(jwtProperties.getAccessExp());
    }

}
