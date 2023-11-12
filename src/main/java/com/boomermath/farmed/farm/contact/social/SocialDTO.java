package com.boomermath.farmed.farm.contact.social;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;
import lombok.Getter;

@Serdeable
@Introspected
@Getter
public class SocialDTO {
    private final SocialType socialType;
    private final String url;

    public SocialDTO(SocialType socialType, String socialInfo) {
        this.socialType = socialType;
        this.url = SocialUrlResolver.urlFromInfo(socialType, socialInfo);
    }
}
