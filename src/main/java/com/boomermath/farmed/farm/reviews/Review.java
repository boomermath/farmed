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
import lombok.Getter;
import lombok.Setter;


@MappedEntity
@Getter
@Setter
public class Review {
    @Id
    @AutoPopulated
    private UUID id;

    private User user;

    private int stars;
    private String text;
    
    @DateCreated
    private LocalDateTime createdAt;
    @DateUpdated
    private LocalDateTime updatedAt;

    private Farm farm;
}
