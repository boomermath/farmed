package com.boomermath.farmed.farm.reviews;

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

@Controller("/farm/{farmId}")
@Secured(SecurityRule.IS_AUTHENTICATED)
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    
    @Get("/reviews")
    public Mono<Page<ReviewResponseDTO>> getReviews(@PathVariable("farmId") UUID farmId, @QueryValue Optional<Integer> page, @QueryValue Optional<Integer> size){
        return reviewRepository.findByFarmIdOrderByUpdatedAtDesc(farmId, Pageable.from(page.orElse(0), size.orElse(10)));
    }

    @Post("/reviews")
    public Mono<ReviewResponseDTO> postReview(Authentication authentication, @PathVariable("farmId") UUID farmId, @Body ReviewBodyDTO reviewDTO) {
        UUID userId = (UUID) authentication.getAttributes().get("id");

        return reviewRepository.existsByUserId(userId)
                .flatMap(b -> 
                    b ? Mono.error(new Exception("REVIEW_POSTED")) : 
                    reviewRepository.save(reviewMapper.toEntity(reviewDTO, userId, farmId))
                    .map(reviewMapper::toDTO)
                );
    }

    @Post("/reviews/{reviewId}")
    public Mono<Review> updateReview(Authentication authentication, @Body ReviewBodyDTO reviewDTO, @PathVariable("farmId") UUID farmId, @PathVariable("reviewId") UUID reviewId) {
        UUID userId = (UUID) authentication.getAttributes().get("id");

        return reviewRepository.updateOne(reviewId, farmId, userId, reviewDTO);
    }

    @Delete("/reviews/{reviewId}")
    public Mono<Long> deleteReview(Authentication authentication, @PathVariable("farmId") UUID farmId, @PathVariable("reviewId") UUID reviewId) {
        UUID userId = (UUID) authentication.getAttributes().get("id");

        return reviewRepository.deleteOne(reviewId, farmId, userId);
    }
}
