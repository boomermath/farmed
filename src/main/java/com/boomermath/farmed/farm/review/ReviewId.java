package com.boomermath.farmed.farm.review;

import io.micronaut.data.annotation.Embeddable;
import io.micronaut.data.annotation.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewId implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "farm_id", insertable = false, updatable = false)
    private UUID farmId;

    @Column(name = "user_id", insertable = false, updatable = false)
    private UUID userId;
}
