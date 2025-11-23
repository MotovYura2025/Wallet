package com.example.walletapi.config;

import com.example.walletapi.service.TwoFactorAuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class TwoFactorAuthenticationFilter extends OncePerRequestFilter {

    public TwoFactorAuthenticationFilter(TwoFactorAuthService twoFactorAuthService) {
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // TODO: имплементация 2FA
        filterChain.doFilter(request, response);
    }
}
