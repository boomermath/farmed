package com.boomermath.farmed.farm.review;

import com.boomermath.farmed.farm.Farm;
import com.boomermath.farmed.farm.FarmRepository;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Singleton
@RequiredArgsConstructor
public class ReviewService {
    private final FarmRepository farmRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public Mono<ReviewDTO> createReview(Review review) {
        return farmRepository.findById(review.getId().getFarmId())
                .flatMap(f -> {
                    int currentReviewCount = f.getReviewCount();

                    f.setRating(
                            ((f.getRating() * currentReviewCount) + review.getStars()) / (currentReviewCount + 1)
                    );
                    f.setReviewCount(currentReviewCount + 1);

                    return Mono.zip(farmRepository.save(f), reviewRepository.save(review));
                })
                .map(Tuple2::getT2)
                .map(reviewMapper::toDTO);
    }

    public Mono<ReviewDTO> updateReview(Review review) {
        return Mono.zip(farmRepository.findById(review.getId().getFarmId()), reviewRepository.findById(review.getId()))
                .map(t -> {
                    Farm f = t.getT1();
                    Review r = t.getT2();

                    int currentReviewCount = f.getReviewCount();

                    f.setRating(
                            ((f.getRating() * currentReviewCount) - r.getStars() + review.getStars()) / (currentReviewCount + 1)
                    );
                    f.setReviewCount(currentReviewCount + 1);

                    return t;
                }).flatMap(
                        t -> Mono.zip(farmRepository.save(t.getT1()), reviewRepository.update(review))
                )
                .map(Tuple2::getT2)
                .map(reviewMapper::toDTO);
    }

    public Mono<Long> deleteReview(ReviewId reviewId) {

    }
}
