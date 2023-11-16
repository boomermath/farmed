package com.boomermath.farmed;


import com.boomermath.farmed.farm.Farm;
import com.boomermath.farmed.farm.FarmRepository;
import com.boomermath.farmed.farm.review.Review;
import com.boomermath.farmed.farm.review.ReviewRepository;
import com.boomermath.farmed.user.User;
import com.boomermath.farmed.user.UserRepository;
import com.boomermath.farmed.user.auth.crypto.PasswordEncoder;
import com.boomermath.farmed.user.auth.identity.Identity;
import com.boomermath.farmed.user.auth.identity.IdentityRepository;
import com.boomermath.farmed.user.auth.identity.IdentityType;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

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
//        Farm farm = farmRepository.findByName("Union Market").block();
//        User user = userRepository.findByUsernameOrEmail(null,"boomermath@gmail.com").block();
//        List<Review> reviews = new ArrayList<>();
//
//        for (int i = 0; i < 10; i++) {
//            Review r = Review.builder()
//                    .user(user)
//                    .farm(farm)
//                    .stars(3)
//                    .text("Review " + i)
//                    .build();
//
//            reviews.add(r);
//        }
//
//        reviewRepository.saveAll(reviews).blockFirst();
    }
}
