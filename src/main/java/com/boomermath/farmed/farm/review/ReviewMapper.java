package com.boomermath.farmed.farm.review;

import com.boomermath.farmed.user.User;
import com.boomermath.farmed.user.UserDTO;
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
            from ="#{review.getId().getId()}"
        ),
         @Mapping(
             to = "user",
             from = "#{review.getUser() != null ? this.toUserDTO(review.getUser()) : null}"
         )
    })
    ReviewDTO toDTO(Review review);

    @Mapper
    UserDTO toUserDTO(User user);
}
