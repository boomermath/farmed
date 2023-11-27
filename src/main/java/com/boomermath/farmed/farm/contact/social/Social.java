package com.boomermath.farmed.farm.contact.social;

import com.boomermath.farmed.farm.Farm;
import com.boomermath.farmed.farm.contact.Contact;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(
    uniqueConstraints = @UniqueConstraint(name = "social_info_constraint", columnNames = {"farm_id", "social_type"})
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Social {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "social_type")
    private SocialType socialType;
    private String socialInfo;
}
