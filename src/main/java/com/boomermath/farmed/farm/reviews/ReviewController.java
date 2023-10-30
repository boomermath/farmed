package com.boomermath.farmed.farm.reviews;

import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.security.authentication.Authentication;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Controller("/farm")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    
    @Get("/{farmId}/reviews")
    public Mono<Page<ReviewResponseDTO>> getReviews(@PathVariable("farmId") UUID farmId, @QueryValue Optional<Integer> page, @QueryValue Optional<Integer> size){
        return reviewRepository.findByFarmIdOrderByUpdateAtDesc(farmId, Pageable.from(page.orElse(0), size.orElse(10)));
    }

    @Post("/{farmId}/reviews")
    public Mono<ReviewResponseDTO> postReview(Authentication authentication, @PathVariable("farmId") UUID farmId, @Body ReviewBodyDTO reviewDTO) {
        UUID userId = (UUID) authentication.getAttributes().get("id");

        return reviewRepository.existsByUserId(userId)
                .flatMap(b -> 
                    b ? Mono.error(new Exception("REVIEW_POSTED")) : 
                    reviewRepository.save(reviewMapper.toEntity(reviewDTO, userId, farmId))
                    .map(reviewMapper::toDTO)
                );
    }

    @Post("/{farmId}/reviews/{reviewId}")
    public Mono<Review> updateReview(Authentication authentication, @Body ReviewBodyDTO reviewDTO, @PathVariable("reviewId") UUID reviewId) {
        UUID userId = (UUID) authentication.getAttributes().get("id");

        Review review = reviewMapper.toEntity(reviewDTO, userId, null);
        review.setId(reviewId);

        return reviewRepository.updateOne(review);
    }

    @Delete("/{farmId}/reviews/{reviewId}")
    public Mono<Long> deleteReview(Authentication authentication, @PathVariable("reviewId") UUID reviewId) {
        UUID userId = (UUID) authentication.getAttributes().get("id");
        return reviewRepository.deleteById(reviewId);
    }
}
