package com.boomermath.farmed.farm.contact;

import com.boomermath.farmed.farm.contact.social.SocialDTO;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

import java.util.List;

@Serdeable
@Introspected
@Data
public class ContactDTO {
    private final String phoneNumber;
    private final String email;
    private final String website;

    private final List<SocialDTO> social;
}
