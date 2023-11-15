package com.boomermath.farmed.user.data;

import com.boomermath.farmed.user.auth.identity.Identity;
import io.micronaut.data.annotation.*;
import jakarta.validation.Constraint;
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
    private LocalDateTime createdAt;

    @Relation(value = Relation.Kind.ONE_TO_ONE, mappedBy = "user", cascade = Relation.Cascade.ALL)
    private Identity identity;
}
