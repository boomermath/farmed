package com.boomermath.farmed.farm.review;

import com.boomermath.farmed.farm.Farm;
import com.boomermath.farmed.user.data.User;
import jakarta.inject.Singleton;

import java.util.UUID;

@Singleton
public class ReviewMapper {
    public Review toEntity(ReviewUpdateDTO reviewDTO, UUID userId, UUID farmId) {
        return Review.builder()
                .user(User.builder().id(userId).build())
                .farm(Farm.builder().id(farmId).build())
                .stars(reviewDTO.getStars())
                .text(reviewDTO.getText())
                .build();
    }

    public ReviewDTO toDTO(Review review) {
        return new ReviewDTO(
            review.getId(), review.getStars(), review.getText(),
             review.getUser(),
             review.getUpdatedAt()
        );
    }
}
