package com.boomermath.farmed.farm.contact;

import com.boomermath.farmed.farm.Farm;
import com.boomermath.farmed.farm.contact.social.Social;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import io.micronaut.data.annotation.sql.JoinColumn;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@MappedEntity
@Getter
@Setter
public class Contact {
    @Id
    private UUID id;

    private String phoneNumber;
    private String email;
    private String website;

    private Farm farm;

    @Relation(value = Relation.Kind.ONE_TO_MANY, mappedBy = "contact", cascade = Relation.Cascade.ALL)
    private List<Social> social;
}
