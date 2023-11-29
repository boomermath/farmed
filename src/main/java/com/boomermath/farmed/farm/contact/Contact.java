package com.boomermath.farmed.farm.contact;

import com.boomermath.farmed.farm.contact.social.Social;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
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

     @OneToMany
     @JoinColumn(name= "farm_id")
     private List<Social> social;
}
