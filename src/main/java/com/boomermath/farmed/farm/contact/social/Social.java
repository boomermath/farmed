package com.boomermath.farmed.farm.contact.social;

import io.micronaut.data.annotation.AutoPopulated;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import com.boomermath.farmed.farm.contact.Contact;

@MappedEntity
@Getter
@Setter
public class Social {
    @Id
    @AutoPopulated
    private UUID id;

    private SocialType socialType;
    private String socialInfo;

    private Contact contact;

    public String getUrl() {
        return SocialUrlResolver.urlFromInfo(socialType, socialInfo);
    }
}
