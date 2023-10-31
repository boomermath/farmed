package com.boomermath.farmed.farm;

import com.boomermath.farmed.farm.reviews.Review;
import io.micronaut.data.annotation.AutoPopulated;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Relation;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@MappedEntity
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
    private List<Review> reviews;
}
