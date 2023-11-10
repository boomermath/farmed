package com.boomermath.farmed.user.auth.provider.email;


import com.boomermath.farmed.user.auth.provider.UserRegisterDTO;
import io.micronaut.core.annotation.Introspected;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Introspected
@Getter
@AllArgsConstructor
public final class EmailAuthDTO implements UserRegisterDTO {
    private final String email;
    private final String password;
    private final String username;
}
