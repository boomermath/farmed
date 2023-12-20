package com.boomermath.farmed.farm.review;

import com.boomermath.farmed.farm.Farm;
import com.boomermath.farmed.farm.FarmRepository;
import io.micronaut.security.authentication.AuthenticationResponse;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Singleton
@RequiredArgsConstructor
@Slf4j
public class ReviewService {
    private enum ReviewStatus {
        CREATE, UPDATE, DELETE
    }

    private final FarmRepository farmRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public Mono<ReviewDTO> createReview(Review review) {
        return farmRepository.findById(review.getId().getFarmId())
                .flatMap(f -> updateFarmAndReview(f, review, ReviewStatus.CREATE))
                .map(reviewMapper::toDTO)
                .onErrorMap(ConstraintViolationException.class, e -> AuthenticationResponse.exception("REVIEW_EXISTS"));
    }

    public Mono<ReviewDTO> updateReview(Review review) {
        return reviewRepository.findById(review.getId())
                .switchIfEmpty(Mono.error(new RuntimeException("INVALID_REVIEW")))
                .flatMap(r -> updateFarmAndReview(r.getFarm(), review, ReviewStatus.UPDATE))
                .map(reviewMapper::toDTO);
    }

    public Mono<Void> deleteReview(ReviewId reviewId) {
        return reviewRepository.findById(reviewId)
                .switchIfEmpty(Mono.error(new RuntimeException("INVALID_REVIEW")))
                .flatMap(r -> updateFarmAndReview(r.getFarm(), r, ReviewStatus.DELETE))
                .then();
    }

    @Transactional
    protected Mono<Review> updateFarmAndReview(Farm farm, Review review, ReviewStatus reviewStatus) {
        int reviewCount = farm.getReviewCount();
        int newRating;

        switch (reviewStatus) {
            case CREATE:
                reviewCount++;
                newRating = ((farm.getRating() * reviewCount) + review.getStars()) / (reviewCount + 1);
                break;
            case UPDATE:
                newRating = ((farm.getRating() * reviewCount) - review.getStars() + review.getStars())
                        / reviewCount;
                break;
            case DELETE:
                if (reviewCount == 1) {
                    newRating = 0;
                    reviewCount = 0;
                } else {
                    reviewCount--;
                    newRating = ((farm.getRating() * reviewCount) - review.getStars())
                            / reviewCount;
                }
                break;
            default:
                throw new IllegalStateException("Invalid review status: " + reviewStatus);
        }

        farm.setRating(newRating);
        farm.setReviewCount(reviewCount);

        Mono<Review> reviewMono = reviewStatus == ReviewStatus.DELETE ? reviewRepository.delete(review)
                .map(l -> review) : reviewRepository.save(review);

        return Mono.zip(farmRepository.save(farm), reviewMono)
                .map(Tuple2::getT2);
    }
}