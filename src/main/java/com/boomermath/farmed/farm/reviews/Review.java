package com.boomermath.farmed.farm.reviews;

import java.time.LocalDateTime;
import java.util.UUID;

import com.boomermath.farmed.farm.Farm;
import com.boomermath.farmed.user.data.User;

import io.micronaut.data.annotation.AutoPopulated;
import io.micronaut.data.annotation.DateCreated;
import io.micronaut.data.annotation.DateUpdated;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@MappedEntity
@RequiredArgsConstructor
@Getter
@Setter
public class Review {
    @Id
    @AutoPopulated
    private UUID id;


    @NonNull
    @Min(0)
    @Max(5)
    private int stars;
    @NonNull
    private String text;
    
    @DateCreated
    private LocalDateTime createdAt;
    @DateUpdated
    private LocalDateTime updatedAt;

    @NonNull
    private User user;

    @NonNull
    private Farm farm;
}
