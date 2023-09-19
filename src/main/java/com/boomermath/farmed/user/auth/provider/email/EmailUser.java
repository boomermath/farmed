package com.boomermath.farmed.user.auth.provider.email;

import io.micronaut.data.annotation.AutoPopulated;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@MappedEntity
@Getter
@Setter
public class EmailUser {
    @Id
    @AutoPopulated
    private UUID id;

    private String email;
    private String password;
}
