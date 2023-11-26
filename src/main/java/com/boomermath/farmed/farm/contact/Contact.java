package com.boomermath.farmed.farm.contact;

import com.boomermath.farmed.farm.contact.social.Social;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Embeddable
@Getter
@Setter
public class Contact {
    private String phoneNumber;
    private String email;
    private String website;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Social> social;
}
