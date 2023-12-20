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
//                                .farmId(UUID.fromString("3c42cd80-f048-412b-be42-5d5095a4a1a8"))
//                                .userId(UUID.fromString("edf7bb32-e0bc-4e84-99a9-d0d02dfaea36"))
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
