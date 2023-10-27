package com.boomermath.farmed.user.auth.crypto;

import io.micronaut.core.annotation.NonNull;

public interface PasswordEncoder {
    String encode(String raw);

    boolean matches(String raw, String original);
}
