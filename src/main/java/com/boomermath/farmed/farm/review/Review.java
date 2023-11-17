package com.boomermath.farmed.farm.review;

import com.boomermath.farmed.farm.Farm;
import com.boomermath.farmed.user.User;
import io.micronaut.data.annotation.*;
import io.micronaut.data.annotation.sql.JoinColumn;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedEntity
@Builder
@Getter
@Setter
public class Review {
    @Id
    @AutoPopulated
    private UUID id;

    @Min(1)
    @Max(5)
    private int stars;
    @NonNull
    private String text;
    
    @DateCreated
    private LocalDateTime createdAt;
    @DateUpdated
    private LocalDateTime updatedAt;

    private User user;
    private Farm farm;
}
