package com.boomermath.farmed;

import com.boomermath.farmed.farm.Farm;
import com.boomermath.farmed.farm.FarmRepository;
import com.boomermath.farmed.farm.reviews.Review;
import com.boomermath.farmed.farm.reviews.ReviewRepository;
import com.boomermath.farmed.user.data.UserRepository;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class MiscBean {

    private final FarmRepository farmRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    @EventListener
    @Transactional
    public void startupEvent(StartupEvent startupEvent) {
        Farm farm = Farm.builder()
                .name("Union")
                .build();

        farm = farmRepository.save(farm).block();

        Review review = Review.builder()
                .user(userRepository.findByEmail("boomermath@gmail.com").block())
                .text("Cool place")
                .stars(3)
                .farm(farm)
                .build();

        reviewRepository.save(review).block();
    }
}
