package com.boomermath.farmed.user;

import com.boomermath.farmed.user.auth.identity.Identity;
import io.micronaut.data.annotation.DateCreated;
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
    @OneToOne(mappedBy = "user")
    private Identity identity;
}
