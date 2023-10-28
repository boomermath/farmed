package com.boomermath.farmed.user.auth.provider;


import com.boomermath.farmed.user.data.User;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.serde.annotation.Serdeable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Serdeable
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserAuthenticationDTO implements Authentication {
    private final User user;
    @Override
    public @NonNull Map<String, Object> getAttributes() {
        return Map.of(
                "id", user.getId(),
                "username", user.getUsername(),
                "user", user
        );
    }

    @Override
    public String getName() {
        return user.getUsername();
    }
    public static UserAuthenticationDTO from(User user) {
        return new UserAuthenticationDTO(user);
    }
}
