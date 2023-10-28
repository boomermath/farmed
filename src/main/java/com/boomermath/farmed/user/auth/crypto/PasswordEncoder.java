package com.boomermath.farmed.user.auth.crypto;

public interface PasswordEncoder {
    String encode(String raw);

    boolean matches(String raw, String original);
}
