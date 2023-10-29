package com.boomermath.farmed.farm.contact;

import io.micronaut.data.annotation.AutoPopulated;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@MappedEntity
@Getter
@Setter
public class Social {
    @Id
    @AutoPopulated
    private UUID id;

    private SocialType socialType;
    private String username;
}
