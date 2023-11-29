package com.boomermath.farmed.farm.review;

import com.boomermath.farmed.farm.Farm;
import com.boomermath.farmed.farm.FarmRepository;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Singleton
@RequiredArgsConstructor
@Slf4j
public class ReviewService {
    private final FarmRepository farmRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Transactional
    public Mono<ReviewDTO> createReview(Review review) {
        return farmRepository.findById(review.getId().getFarmId())
                .map(f -> {
                    int currentReviewCount = f.getReviewCount();

                    f.setRating(
                            ((f.getRating() * currentReviewCount) + review.getStars()) / (currentReviewCount + 1));
                    f.setReviewCount(currentReviewCount + 1);

                    return f;
                })
                .flatMap(f -> Mono.zip(farmRepository.save(f), reviewRepository.save(review)))
                .map(Tuple2::getT2)
                .map(r -> {
                    log.info(r.toString());
                    return r;
                })
                .map(reviewMapper::toDTO);
    }

    @Transactional
    public Mono<ReviewDTO> updateReview(Review review) {
        return reviewRepository.findById(review.getId())
                .switchIfEmpty(Mono.error(new RuntimeException("INVALID_REVIEW")))
                .map(r -> {
                    Farm f = r.getFarm();

                    int currentReviewCount = f.getReviewCount();

                    f.setRating(
                            ((f.getRating() * currentReviewCount) - r.getStars() + review.getStars())
                                    / (currentReviewCount));

                    return r;
                })
                .flatMap(r -> Mono.zip(farmRepository.save(r.getFarm()), reviewRepository.save(r)))
                .map(Tuple2::getT2)
                .map(reviewMapper::toDTO);
    }

    @Transactional
    public Mono<Void> deleteReview(ReviewId reviewId) {
        return reviewRepository.findById(reviewId)
                .switchIfEmpty(Mono.error(new RuntimeException("INVALID_REVIEW")))
                .map(r -> {
                    Farm f = r.getFarm();

                    int currentReviewCount = f.getReviewCount();

                    if (currentReviewCount == 1) {
                        f.setRating(0);
                        f.setReviewCount(0);
                    } else {
                        f.setRating(
                                ((f.getRating() * currentReviewCount) - r.getStars())
                                        / (currentReviewCount - 1));
                        f.setReviewCount(currentReviewCount - 1);
                    }

                    return r;
                })
                .flatMap(r -> Mono.zip(farmRepository.save(r.getFarm()), reviewRepository.delete(r)))
                .then();
    }
}
