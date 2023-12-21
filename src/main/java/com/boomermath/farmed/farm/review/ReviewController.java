package com.boomermath.farmed.farm.review;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.authentication.AuthenticationFailureReason;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.rules.SecurityRule;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

@Controller("/farm/{farmId}/reviews")
@Secured(SecurityRule.IS_AUTHENTICATED)
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewRepository reviewRepository;
    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    private UUID getUserId(Authentication authentication) {
        if (authentication == null || authentication.getAttributes() == null) {
            throw AuthenticationResponse.exception(AuthenticationFailureReason.USER_NOT_FOUND);
        }

        String userId = (String) authentication.getAttributes().get("id");

        return UUID.fromString(userId);
    }

    @Get
    public Mono<Page<ReviewDTO>> getReviews(@PathVariable("farmId") UUID farmId, @QueryValue Optional<Integer> page, @QueryValue Optional<Integer> size) {
        return reviewRepository
                .findByFarmIdOrderByUpdatedAtDesc(farmId, Pageable.from(page.orElse(0), size.orElse(10)))
                .map(p -> p.map(reviewMapper::toDTO));
    }

    @Post
    public Mono<ReviewDTO> postReview(Authentication authentication, @PathVariable("farmId") UUID farmId, @Body ReviewRequestDTO reviewDTO) {
        ReviewId reviewId = ReviewId.builder()
                .userId(getUserId(authentication))
                .farmId(farmId)
                .build();

        return reviewService.createReview(reviewMapper.toEntity(reviewId, reviewDTO));
    }

    @Post("/{reviewId}")
    public Mono<ReviewDTO> updateReview(Authentication authentication, @Body ReviewRequestDTO reviewDTO, @PathVariable("farmId") UUID farmId, @PathVariable("reviewId") UUID reqReviewId) {
        ReviewId reviewId = ReviewId.builder()
                .id(reqReviewId)
                .userId(getUserId(authentication))
                .farmId(farmId)
                .build();

        return reviewService.updateReview(reviewMapper.toEntity(reviewId, reviewDTO));
    }

    @Delete("/{reviewId}")
    public Mono<Void> deleteReview(Authentication authentication, @PathVariable("farmId") UUID farmId, @PathVariable("reviewId") UUID reqReviewId) {
        ReviewId reviewId = ReviewId.builder()
                .id(reqReviewId)
                .userId(getUserId(authentication))
                .farmId(farmId)
                .build();

        return reviewService.deleteReview(reviewId);
    }
}
