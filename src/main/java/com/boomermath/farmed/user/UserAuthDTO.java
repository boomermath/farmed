package com.boomermath.farmed.user;

import java.util.UUID;

import com.boomermath.farmed.user.auth.identity.Identity;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

@Introspected
@Data
public class UserAuthDTO {
    private final UUID id;
    private final String username; 
    private final Identity identity;
}
