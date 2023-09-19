package com.boomermath.farmed.user.auth.crypto;

import io.micronaut.core.annotation.NonNull;

public interface PasswordEncoder {
    String encode(String rawPass);

    boolean matches(String rawPass, String currentPass);
}
