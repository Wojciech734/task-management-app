package com.example.taskManagementApp.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

public class JwtValidator extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String jwt = request.getHeader("Authorization");
        if (jwt != null) {
            try {
                String email = JwtProvider.getEmailFromJwtToken(jwt);
                ArrayList<GrantedAuthority> authorities = new ArrayList<>();
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                email,
                                null,
                                authorities
                        );
            } catch (Exception e) {
                throw new BadCredentialsException("Invalid token...");
            }
        }
        filterChain.doFilter(request, response);
    }
}
