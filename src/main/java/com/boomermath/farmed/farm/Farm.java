package com.boomermath.farmed.farm;

import com.boomermath.farmed.farm.contact.Contact;
import com.boomermath.farmed.farm.contact.social.Social;
import com.boomermath.farmed.farm.review.Review;
import com.boomermath.farmed.farm.schedule.DailySchedule;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Farm {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NonNull
    private String name;

    @Builder.Default
    private double rating = 0;

    @Builder.Default
    private int reviewCount = 0;

    @ToString.Exclude
    @OneToMany(mappedBy = "farm")
    private List<Review> reviews;

    @ToString.Exclude
    @Embedded
    private Contact contact;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "farm")
    private List<DailySchedule> schedule;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "farm")
    private List<Social> social;
}
