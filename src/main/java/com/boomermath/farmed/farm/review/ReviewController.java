package com.boomermath.farmed.farm.review;

import io.micronaut.data.model.Pageable;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

@Controller("/farm/{farmId}")
@Secured(SecurityRule.IS_AUTHENTICATED)
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewRepository reviewRepository;
    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;
    
    @Get("/reviews")
    public Flux<Review> getReviews(@PathVariable("farmId") UUID farmId, @QueryValue Optional<Integer> page,
                                   @QueryValue Optional<Integer> size) {
        return reviewRepository.findByFarmIdOrderByUpdatedAtDesc(farmId,
                Pageable.from(page.orElse(0), size.orElse(10)));
    }

    @Post("/reviews")
    public Mono<ReviewDTO> postReview(Authentication authentication, @PathVariable("farmId") UUID farmId,
                                      @Body ReviewRequestDTO reviewDTO) {
        String userId = (String) authentication.getAttributes().get("id");

        ReviewId reviewId = ReviewId.builder()
                .farmId(farmId)
                .userId(UUID.fromString(userId))
                .build();

        return reviewService.createReview(reviewMapper.toEntity(reviewId, reviewDTO));
    }

    @Post("/reviews/{reviewId}")
    public Mono<ReviewDTO> updateReview(Authentication authentication, @Body ReviewRequestDTO reviewDTO,
                                     @PathVariable("farmId") UUID farmId, @PathVariable("reviewId") UUID requestedReviewId) {
        String userId = (String) authentication.getAttributes().get("id");

        ReviewId reviewId = ReviewId.builder()
                .id(requestedReviewId)
                .farmId(farmId)
                .userId(UUID.fromString(userId))
                .build();

        return reviewService.updateReview(reviewMapper.toEntity(reviewId, reviewDTO));
    }

    @Delete("/reviews/{reviewId}")
    public Mono<Void> deleteReview(Authentication authentication, @PathVariable("farmId") UUID farmId,
                                   @PathVariable("reviewId") UUID requestedReviewId) {
        String userId = (String) authentication.getAttributes().get("id");

        ReviewId reviewId = ReviewId.builder()
                .id(requestedReviewId)
                .farmId(farmId)
                .userId(UUID.fromString(userId))
                .build();

        return reviewService.deleteReview(reviewId);
    }
}
