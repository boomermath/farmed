package com.boomermath.farmed.farm;

import io.micronaut.data.annotation.AutoPopulated;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@MappedEntity
@Getter
@Setter
public class Farm {
    @Id
    @AutoPopulated
    public UUID id;

    public String name;

    public String website;
}
