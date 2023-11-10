package com.boomermath.farmed.user.data;

import com.boomermath.farmed.user.auth.identity.Identity;
import io.micronaut.data.annotation.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedEntity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    @Id
    @AutoPopulated
    private UUID id;

    @NonNull
    @Email(message = "INVALID_EMAIL")
    private String email;

    @NonNull
    private String username;

    @DateCreated
    @MappedProperty(value = "created_at", alias = "createdAt")
    private LocalDateTime createdAt;

    @Relation(value = Relation.Kind.ONE_TO_ONE, mappedBy = "user")
    private Identity identity;
}
