package com.boomermath.farmed.farm.contact;

import com.boomermath.farmed.farm.Farm;
import com.boomermath.farmed.farm.contact.social.Social;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String phoneNumber;
    private String email;
    private String website;

    @OneToOne
    private Farm farm;

    @OneToMany
    private List<Social> social;
}
