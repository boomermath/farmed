package com.boomermath.farmed.farm.review;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import io.r2dbc.spi.R2dbcDataIntegrityViolationException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

import com.boomermath.farmed.farm.Farm;
import com.boomermath.farmed.farm.FarmRepository;
import com.boomermath.farmed.user.User;

@Controller("/farm/{farmId}")
@Secured(SecurityRule.IS_AUTHENTICATED)
@RequiredArgsConstructor
public class ReviewController {
    private final FarmRepository farmRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Get("/reviews")
    public Mono<Page<Review>> getReviews(@PathVariable("farmId") UUID farmId, @QueryValue Optional<Integer> page,
            @QueryValue Optional<Integer> size) {
        return reviewRepository.findByFarmIdOrderByUpdatedAtDesc(farmId,
                Pageable.from(page.orElse(0), size.orElse(10)));
    }

    @Post("/reviews")
    public Mono<ReviewDTO> postReview(Authentication authentication, @PathVariable("farmId") UUID farmId,
            @Body ReviewRequestDTO reviewDTO) {
        return farmRepository.findById(farmId)
                .flatMap(f -> Mono.defer(() -> {
                    UUID userId = (UUID) authentication.getAttributes().get("id");
                    String username = authentication.getName();
            
                    Review review = reviewMapper.toEntity(reviewDTO);
            
                    User user = User.builder().id(userId).username(username).build();
            
                    review.setFarm(f);
                    review.setUser(user);

                    return reviewRepository.save(review)
                        .onErrorMap(R2dbcDataIntegrityViolationException.class, e -> new Exception("REVIEW_EXISTS"))
                        .map(reviewMapper::toDTO);
                }))
                .switchIfEmpty(Mono.error(new Exception("INVALID_FARM")));

    }

    @Post("/reviews/{reviewId}")
    public Mono<Review> updateReview(Authentication authentication, @Body ReviewRequestDTO reviewDTO,
            @PathVariable("farmId") UUID farmId, @PathVariable("reviewId") UUID reviewId) {
        UUID userId = (UUID) authentication.getAttributes().get("id");

        return reviewRepository.updateOne(reviewId, farmId, userId, reviewDTO);
    }

    @Delete("/reviews/{reviewId}")
    public Mono<Long> deleteReview(Authentication authentication, @PathVariable("farmId") UUID farmId,
            @PathVariable("reviewId") UUID reviewId) {
        UUID userId = (UUID) authentication.getAttributes().get("id");

        return reviewRepository.deleteOne(reviewId, farmId, userId);
    }
}
