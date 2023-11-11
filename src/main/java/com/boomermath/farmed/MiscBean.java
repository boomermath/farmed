package com.boomermath.farmed;


import java.util.UUID;

import com.boomermath.farmed.farm.Farm;
import com.boomermath.farmed.farm.FarmRepository;
import com.boomermath.farmed.farm.review.Review;
import com.boomermath.farmed.farm.review.ReviewRepository;
import com.boomermath.farmed.user.auth.crypto.PasswordEncoder;
import com.boomermath.farmed.user.auth.identity.Identity;
import com.boomermath.farmed.user.auth.identity.IdentityRepository;
import com.boomermath.farmed.user.auth.identity.IdentityType;
import com.boomermath.farmed.user.data.User;
import com.boomermath.farmed.user.data.UserRepository;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Singleton
@RequiredArgsConstructor
@Slf4j
public class MiscBean {

    private final FarmRepository farmRepository;
    private final UserRepository userRepository;
    private final IdentityRepository identityRepository;
    private final ReviewRepository reviewRepository;
    private final PasswordEncoder encoder;

    private void createUser(String email, String username, String password) {
        User user = User.builder()
        .email(email)
        .username(username)
        .build();

        user = userRepository.save(user).block();

        Identity identity = Identity.builder()
        .hash(encoder.encode(password))
        .identityType(IdentityType.EMAIL)
        .user(user)
        .build();

        identityRepository.save(identity).block();
    }

    @EventListener
    @Transactional
    public void startupEvent(StartupEvent startupEvent) {

        createUser("boomermath@gmail.com", "boomermath", "daone");
        createUser("boomermath1@gmail.com", "boomermath1", "daone");


        Farm farm = Farm.builder()
        .id(UUID.fromString("b65b35eb-b48a-400e-805c-47a4e907d669"))
                .name("Union")
                .build();

        farm = farmRepository.save(farm).block();
        log.info(farm.getId().toString());
        Review review = Review.builder()
                .user(userRepository.findByEmail("boomermath@gmail.com").block())
                .text("Cool place")
                .stars(3)
                .farm(farm)
                .build();

        Review review1 = Review.builder()
                .user(userRepository.findByEmail("boomermath1@gmail.com").block())
                .text("Coolest place")
                .stars(5)
                .farm(farm)
                .build();

        reviewRepository.save(review).block();
        reviewRepository.save(review1).block();
    }
}
