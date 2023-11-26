package com.boomermath.farmed.farm.review;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
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

    private ReviewIdDTO createReviewId(Authentication authentication, UUID farmId, UUID reviewId) {
        UUID userId = UUID.fromString(authentication.getAttributes().get("id").toString());

        return ReviewIdDTO.builder()
                .farmId(farmId)
                .userId(userId)
                .id(reviewId)
                .build();
    }

    @Get
    public Mono<Page<Review>> getReviews(@PathVariable("farmId") UUID farmId, @QueryValue Optional<Integer> page,
                                 @QueryValue Optional<Integer> size) {
        return reviewRepository.findByFarmIdOrderByUpdatedAtDesc(farmId,
                Pageable.from(page.orElse(0), size.orElse(10)));
    }

    @Post
    public Mono<ReviewDTO> postReview(Authentication authentication, @PathVariable("farmId") UUID farmId,
                                      @Body ReviewRequestDTO reviewDTO) {
        return reviewService.createReview(
                reviewMapper.toEntity(createReviewId(authentication, farmId, null), reviewDTO)
        );
    }

    @Put("/{reviewId}")
    public Mono<ReviewDTO> updateReview(Authentication authentication, @Body ReviewRequestDTO reviewDTO,
                                        @PathVariable("farmId") UUID farmId, @PathVariable("reviewId") UUID reviewId) {
        return reviewService.updateReview(
                reviewMapper.toEntity(createReviewId(authentication, farmId, reviewId), reviewDTO)
        );
    }

    @Delete("/{reviewId}")
    public Mono<Void> deleteReview(Authentication authentication, @PathVariable("farmId") UUID farmId,
                                   @PathVariable("reviewId") UUID reviewId) {
        return reviewService.deleteReview(createReviewId(authentication, farmId, reviewId));
    }
}
