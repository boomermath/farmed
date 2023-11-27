package com.boomermath.farmed.farm.review;

import com.boomermath.farmed.farm.Farm;
import com.boomermath.farmed.user.User;
import io.micronaut.data.annotation.DateCreated;
import io.micronaut.data.annotation.DateUpdated;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(name = "review_constraint", columnNames = {"farm_id", "user_id"})
)
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Review {
    @EmbeddedId
    private ReviewId id;

    @Min(1)
    @Max(5)
    private int stars;
    @NonNull
    private String text;

    @DateCreated
    private LocalDateTime createdAt;
    @DateUpdated
    private LocalDateTime updatedAt;

    @ManyToOne
    @MapsId("user_id")
    @ToString.Exclude
    private User user;

    @ManyToOne
    @MapsId("farm_id")
    @ToString.Exclude
    private Farm farm;
}
