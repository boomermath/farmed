package com.boomermath.farmed.user.auth.data;

import com.boomermath.farmed.user.data.User;
import io.micronaut.data.annotation.DateCreated;
import io.micronaut.data.annotation.DateUpdated;
import io.micronaut.data.annotation.MappedEntity;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedEntity
@Getter
@Setter
@RequiredArgsConstructor
public class Identity {

    private UUID id;

    @NonNull
    private IdentityType identityType;
    @NonNull
    private String hash;
    @NonNull
    private User user;

    @DateCreated
    private LocalDateTime createdAt;
    @DateUpdated
    private LocalDateTime updatedAt;
}
