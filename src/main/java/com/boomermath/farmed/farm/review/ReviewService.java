package com.boomermath.farmed.farm.review;

import com.boomermath.farmed.farm.Farm;
import com.boomermath.farmed.farm.FarmRepository;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Singleton
@RequiredArgsConstructor
public class ReviewService {
    private final FarmRepository farmRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public Mono<ReviewDTO> createReview(Review review) {
        return farmRepository.findById(review.getId().getFarmId())
                .map(f -> {
                    int currentReviewCount = f.getReviewCount();

                    f.setRating(
                            ((f.getRating() * currentReviewCount) + review.getStars()) / (currentReviewCount + 1));
                    f.setReviewCount(currentReviewCount + 1);
                    review.setFarm(f);

                    return review;
                })
                .flatMap(reviewRepository::save)
                .map(reviewMapper::toDTO);
    }

    public Mono<ReviewDTO> updateReview(Review review) {
        return reviewRepository.findById(review.getId())
                .switchIfEmpty(Mono.error(new Exception("INVALID_REVIEW")))
                .map(r -> {
                    Farm f = r.getFarm();

                    int currentReviewCount = f.getReviewCount();

                    f.setRating(
                            ((f.getRating() * currentReviewCount) - r.getStars() + review.getStars())
                                    / (currentReviewCount + 1));
                    f.setReviewCount(currentReviewCount + 1);

                    return r;
                })
                .flatMap(reviewRepository::save)
                .map(reviewMapper::toDTO);
    }

    public Mono<Void> deleteReview(ReviewId reviewId) {
        return reviewRepository.delete(reviewId)
        .switchIfEmpty(Mono.error(new Exception("INVALID_REVIEW")))
        .map(r -> {
            Farm f = r.getFarm();

            int currentReviewCount = f.getReviewCount();

            f.setRating(
                    ((f.getRating() * currentReviewCount) - r.getStars())
                            / (currentReviewCount - 1));
            f.setReviewCount(currentReviewCount - 1);

            return f;
        })
        .flatMap(farmRepository::save)
        .then();
    }
}
