package com.boomermath.farmed.user.auth.crypto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.inject.Singleton;

@Singleton
public class BCryptEncoder implements PasswordEncoder {
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public String encode(String rawPass) {
        return bCryptPasswordEncoder.encode(rawPass);
    }

    @Override
    public boolean matches(String rawPass, String currentPass) {
        return bCryptPasswordEncoder.matches(rawPass, currentPass);
    }
}
