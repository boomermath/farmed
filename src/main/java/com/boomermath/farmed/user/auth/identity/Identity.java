package com.boomermath.farmed.user.auth.identity;

import com.boomermath.farmed.user.data.User;
import io.micronaut.data.annotation.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedEntity
@Getter
@Setter
@Builder
public class Identity {

    @Id
    @AutoPopulated
    private UUID id;

    @NonNull
    @MappedProperty(value = "identity_type", alias = "identityType")
    private IdentityType identityType;
    @NonNull
    private String hash;

    @NonNull
    private User user;

    @DateCreated
    @MappedProperty(value = "created_at", alias = "createdAt")
    private LocalDateTime createdAt;

    @DateUpdated
    @MappedProperty(value = "updated_at", alias = "updatedAt")
    private LocalDateTime updatedAt;
}
