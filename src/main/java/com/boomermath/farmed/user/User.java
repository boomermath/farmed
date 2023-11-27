package com.boomermath.farmed.user;

import com.boomermath.farmed.user.auth.identity.Identity;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.data.annotation.DateCreated;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "farmed_user")
@Getter
@Setter
@Builder
@ToString
@Serdeable
@Introspected
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NonNull
    @Email(message = "INVALID_EMAIL")
    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String username;

    @DateCreated
    private LocalDateTime createdAt;

    @ToString.Exclude
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Identity identity;
}
