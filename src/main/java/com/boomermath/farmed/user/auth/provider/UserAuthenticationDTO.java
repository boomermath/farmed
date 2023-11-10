package com.boomermath.farmed.user.auth.provider;


import com.boomermath.farmed.user.data.User;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.security.authentication.Authentication;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class UserAuthenticationDTO implements Authentication {
    private final User user;
    @Override
    public @NonNull Map<String, Object> getAttributes() {
        return Map.of(
                "id", user.getId()
        );
    }

    @Override
    public String getName() {
        return user.getUsername();
    }
}
