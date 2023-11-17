package com.boomermath.farmed.user;

import com.boomermath.farmed.user.auth.identity.Identity;
import io.micronaut.data.annotation.*;
import jakarta.validation.Constraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedEntity("farmed_user")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    @Id
    @AutoPopulated
    private UUID id;

    @NonNull
    @Email(message = "INVALID_EMAIL")
    private String email;
    
    private String username;

    @DateCreated
    private LocalDateTime createdAt;

    @ToString.Exclude
    @Relation(value = Relation.Kind.ONE_TO_ONE, mappedBy = "user", cascade = Relation.Cascade.ALL)
    private Identity identity;
}
