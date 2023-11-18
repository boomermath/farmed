package com.boomermath.farmed.user.auth.identity;

import com.boomermath.farmed.user.User;
import io.micronaut.data.annotation.DateCreated;
import io.micronaut.data.annotation.DateUpdated;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Identity {
    @Id
    @Column(name = "user_id")
    private UUID id;

    @NonNull
    private IdentityType identityType;

    @NonNull
    private String hash;

    @ToString.Exclude
    @MapsId
    @OneToOne
    private User user;

    @DateCreated
    private LocalDateTime createdAt;

    @DateUpdated
    private LocalDateTime updatedAt;
}
