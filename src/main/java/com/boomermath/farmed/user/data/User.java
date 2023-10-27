package com.boomermath.farmed.user.data;

import com.boomermath.farmed.user.auth.data.Identity;
import io.micronaut.data.annotation.AutoPopulated;
import io.micronaut.data.annotation.DateCreated;
import io.micronaut.data.annotation.Id;
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
public class User {
    @Id
    @AutoPopulated
    private UUID id;

    @NonNull
    private String email;
    @NonNull
    private String username;
    @DateCreated
    private LocalDateTime createdAt;
    private Identity login;
}
