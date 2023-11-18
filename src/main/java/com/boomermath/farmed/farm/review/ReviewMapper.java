package com.boomermath.farmed.farm.review;

import io.micronaut.context.annotation.Mapper;
import jakarta.inject.Singleton;

@Singleton
public interface ReviewMapper {
    default Review toEntity(ReviewId reviewId, ReviewRequestDTO reviewRequestDTO) {
        return Review.builder()
                .id(reviewId)
                .stars(reviewRequestDTO.getStars())
                .text(reviewRequestDTO.getText())
                .build();
    }

    @Mapper
    ReviewDTO toDTO(Review review);
}
