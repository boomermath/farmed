package com.boomermath.farmed.farm.review;

import io.micronaut.context.annotation.Mapper;
import io.micronaut.context.annotation.Mapper.Mapping;
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

    @Mapper({
            @Mapping(
                    to = "id",
                    from = "#{review.id.id}"
            ),
            @Mapping(
                    to = "timestamp",
                    from = "#{review.updatedAt}"
            ),
            @Mapping(
                    to = "updated",
                    from = "#{!review?.createdAt?.isEqual(review.updatedAt)}"
            )
    })
    ReviewDTO toDTO(Review review);
}
