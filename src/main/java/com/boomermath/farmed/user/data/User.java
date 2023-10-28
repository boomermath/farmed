package com.boomermath.farmed.user.data;

import com.boomermath.farmed.user.auth.identity.Identity;
import io.micronaut.data.annotation.*;
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
    @MappedProperty(value = "created_at", alias = "createdAt")
    private LocalDateTime createdAt;

  //  @Relation(value = Relation.Kind.ONE_TO_ONE, mappedBy = "user")
    private Identity identity;
}
