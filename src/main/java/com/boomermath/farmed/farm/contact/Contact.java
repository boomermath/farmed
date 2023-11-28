package com.boomermath.farmed.farm.contact;

import com.boomermath.farmed.farm.contact.social.Social;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

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
