package com.boomermath.farmed.farm.reviews;

import java.util.UUID;

import com.boomermath.farmed.farm.Farm;
import com.boomermath.farmed.user.data.User;
import com.boomermath.farmed.user.data.UserDTO;

import jakarta.inject.Singleton;

@Singleton
public class ReviewMapper {
    public Review toEntity(ReviewBodyDTO reviewDTO, UUID userId, UUID farmId) {
        return new Review(
            reviewDTO.getStars(),
            reviewDTO.getText(),
            new User(userId),
            new Farm(farmId)
        );
    }

    public ReviewResponseDTO toDTO(Review review) {
        User user = review.getUser();

        return new ReviewResponseDTO(
            review.getId(), review.getStars(), review.getText(),
             new UserDTO(user.getId(), user.getUsername()),
             review.getUpdatedAt()
        );
    }
}
