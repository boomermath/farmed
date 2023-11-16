package com.boomermath.farmed.farm.review;

import io.micronaut.context.annotation.Mapper;
import jakarta.inject.Singleton;

@Singleton
public interface ReviewMapper {
    @Mapper
    Review toEntity(ReviewRequestDTO reviewRequestDTO);

    @Mapper
    ReviewDTO toDTO(Review review);
}
