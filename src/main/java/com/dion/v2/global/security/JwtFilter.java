package com.dion.v2.global.security;

import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        String path = request.getServletPath();

        if(header != null) {
            String[] rawTokens = header.split("Bearer ");

            if(path.startsWith("/auth/**")) {
                Authentication auth = jwtProvider.userAuthentication(rawTokens[1]);
                SecurityContextHolder.getContext().setAuthentication(auth);
            } else if(path.startsWith("/owner/**")) {
                Authentication auth = jwtProvider.ownerAuthentication(rawTokens[1]);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        filterChain.doFilter(request, response);
    }
}



