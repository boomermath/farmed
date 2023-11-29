package com.boomermath.farmed;

import com.boomermath.farmed.farm.FarmRepository;
import com.boomermath.farmed.farm.review.Review;
import com.boomermath.farmed.farm.review.ReviewId;
import com.boomermath.farmed.farm.review.ReviewRepository;
import io.micronaut.runtime.event.ApplicationStartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Singleton
@RequiredArgsConstructor
@Slf4j
public class FarmedSeedingBean {
    private final ReviewRepository reviewRepository;

    @EventListener
    public void appStart(ApplicationStartupEvent event) {
//        Review review = Review
//                .builder()
//                .id(
//                        ReviewId.builder()
//                                .id(UUID.randomUUID())
//                                .userId(UUID.fromString("2be54d6d-e0f2-4817-9085-90ddfe12103a"))
//                                .farmId(UUID.fromString("41caae5d-2bda-4849-abb0-f0295b1af0f8"))
//                                .build()
//                )
//                .stars(3)
//                .text("hi")
//                .build();
//
//        review = reviewRepository.save(review).block();
//
//        assert review != null;
//        log.info(review.toString());
    }
}
