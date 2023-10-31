package com.boomermath.farmed.farm.reviews;

import java.util.UUID;

import com.boomermath.farmed.farm.Farm;
import com.boomermath.farmed.user.data.User;
import com.boomermath.farmed.user.data.UserDTO;

import jakarta.inject.Singleton;

@Singleton
public class ReviewMapper {
    public Review toEntity(ReviewBodyDTO reviewDTO, UUID userId, UUID farmId) {
        return Review.builder()
                .user(User.builder().id(userId).build())
                .farm(Farm.builder().id(farmId).build())
                .stars(reviewDTO.getStars())
                .text(reviewDTO.getText())
                .build();
    }

    public ReviewResponseDTO toDTO(Review review) {
        User user = review.getUser();

        return new ReviewResponseDTO(
            review.getId(), review.getStars(), review.getText(),
             user,
             review.getUpdatedAt()
        );
    }
}
