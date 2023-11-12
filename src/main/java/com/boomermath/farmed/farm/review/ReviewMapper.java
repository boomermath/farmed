package com.boomermath.farmed.farm.review;

import com.boomermath.farmed.farm.Farm;
import com.boomermath.farmed.user.data.User;

import io.micronaut.context.annotation.Mapper;
import jakarta.inject.Singleton;

import java.util.UUID;

@Singleton
public interface ReviewMapper {
    @Mapper
    Review toEntity(ReviewRequestDTO reviewRequestDTO);

    @Mapper
    ReviewDTO toDTO(Review review);
}
