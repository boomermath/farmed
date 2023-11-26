package com.boomermath.farmed.farm;

import com.boomermath.farmed.farm.contact.Contact;
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

    private int rating;
    private int reviewCount;

    @ToString.Exclude
    @OneToMany(mappedBy = "farm")
    private List<Review> reviews;

    @ToString.Exclude
    @Embedded
    private Contact contact;

    @ToString.Exclude
    @OneToMany(mappedBy = "farm")
    private List<DailySchedule> schedule;
}
