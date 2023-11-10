package com.boomermath.farmed.farm.contact;

import io.micronaut.data.annotation.AutoPopulated;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

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

    @Relation(value = Relation.Kind.ONE_TO_MANY, mappedBy = "contact", cascade = Relation.Cascade.ALL)
    private List<Social> social;
}
