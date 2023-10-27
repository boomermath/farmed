package com.boomermath.farmed.user.auth.crypto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.inject.Singleton;

@Singleton
public class BCryptPassEncoder implements PasswordEncoder {
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public String encode(String raw) {
        return bCryptPasswordEncoder.encode(raw);
    }

    @Override
    public boolean matches(String raw, String original) {
        return bCryptPasswordEncoder.matches(raw, original);
    }
}
