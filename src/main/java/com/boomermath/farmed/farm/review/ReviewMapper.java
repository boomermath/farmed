package com.boomermath.farmed.farm.review;

import io.micronaut.context.annotation.Mapper;
import io.micronaut.context.annotation.Mapper.Mapping;
import jakarta.inject.Singleton;

@Singleton
public interface ReviewMapper {
    default Review toEntity(ReviewIdDTO reviewId, ReviewRequestDTO reviewRequestDTO) {
        return Review.builder()
                .id(reviewId.getId())
                .farmId(reviewId.getFarmId())
                .userId(reviewId.getUserId())
                .stars(reviewRequestDTO.getStars())
                .text(reviewRequestDTO.getText())
                .build();
    }

    @Mapper(
            {
                    @Mapping(
                            to = "wasUpdated",
                            from = "#{true}"
                    )
            }
    )
    ReviewDTO toDTO(Review review);
}
