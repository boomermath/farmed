package com.boomermath.farmed.farm.contact;

import io.micronaut.data.annotation.AutoPopulated;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
import java.util.List;

@MappedEntity
@Getter
@Setter
public class Contact {
    @Id
    @AutoPopulated
    private UUID id;

    private String phoneNumber;
    private String email;
    private String website;

    private List<Social> social;
}
