package com.boomermath.farmed.farm.contact.social;

import com.boomermath.farmed.farm.contact.Contact;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Social {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private SocialType socialType;
    private String socialInfo;

    @ManyToOne
    private Contact contact;
}
