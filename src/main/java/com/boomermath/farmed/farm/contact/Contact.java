package com.boomermath.farmed.farm.contact;

import com.boomermath.farmed.farm.contact.social.Social;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Embeddable
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    private String phoneNumber;
    private String email;
    private String website;
}
