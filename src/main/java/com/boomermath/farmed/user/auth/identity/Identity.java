package com.boomermath.farmed.user.auth.identity;

import com.boomermath.farmed.user.User;
import io.micronaut.data.annotation.*;
import io.micronaut.data.annotation.sql.JoinColumn;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedEntity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Identity {
    @Id
    @AutoPopulated
    @MappedProperty()
    private UUID id;

    @NonNull
    private IdentityType identityType;

    @NonNull
    private String hash;

    @ToString.Exclude
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @DateCreated
    @MappedProperty(value = "created_at", alias = "createdAt")
    private LocalDateTime createdAt;

    @DateUpdated
    @MappedProperty(value = "updated_at", alias = "updatedAt")
    private LocalDateTime updatedAt;
}
