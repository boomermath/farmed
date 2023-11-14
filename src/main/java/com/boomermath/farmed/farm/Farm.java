package com.boomermath.farmed.farm;

import io.micronaut.data.annotation.AutoPopulated;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

import com.boomermath.farmed.farm.contact.Contact;
import com.boomermath.farmed.farm.review.Review;
import com.boomermath.farmed.farm.schedule.DailySchedule;

@MappedEntity
@Serdeable
@Builder
@Getter
@Setter
public class Farm {
    @Id
    @AutoPopulated
    private UUID id;

    @NonNull
    private String name;

    @Relation(value = Relation.Kind.ONE_TO_MANY, mappedBy = "farm", cascade = Relation.Cascade.ALL)
    @Nullable
    private List<Review> reviews;

    @Relation(value = Relation.Kind.ONE_TO_ONE, mappedBy = "farm", cascade = Relation.Cascade.ALL)
    private Contact contact;

    @Relation(value = Relation.Kind.ONE_TO_MANY, mappedBy = "farm", cascade = Relation.Cascade.ALL)
    private List<DailySchedule> schedule;
}
