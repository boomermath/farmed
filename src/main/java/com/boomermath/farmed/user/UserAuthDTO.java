package com.boomermath.farmed.user;

import com.boomermath.farmed.user.auth.identity.Identity;
import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import java.util.UUID;

@Introspected
@Data
public class UserAuthDTO {
    private final UUID id;
    private final String username;
    private final Identity identity;
}
