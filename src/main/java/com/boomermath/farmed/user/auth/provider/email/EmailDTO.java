package com.boomermath.farmed.user.auth.provider.email;


import com.boomermath.farmed.user.auth.dto.UserRegisterDTO;

import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Introspected
@Getter
@AllArgsConstructor
public final class EmailDTO {
    @Email
    private final String email;
    private final String password;
}
