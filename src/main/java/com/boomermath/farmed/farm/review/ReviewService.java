package com.boomermath.farmed.farm.review;

import com.boomermath.farmed.farm.Farm;
import com.boomermath.farmed.farm.FarmRepository;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        return updateFarmAndReview(review, ReviewStatus.CREATE)
                .map(reviewMapper::toDTO);
    }

    public Mono<ReviewDTO> updateReview(Review review) {
        return updateFarmAndReview(review, ReviewStatus.UPDATE)
                .map(reviewMapper::toDTO);
    }

    public Mono<Void> deleteReview(ReviewId reviewId) {
        Review review = Review.builder()
                .id(reviewId)
                .text("")
                .stars(0)
                .build();

        return updateFarmAndReview(review, ReviewStatus.DELETE)
                .then();
    }

    protected Mono<Review> updateFarmAndReview(Review review, ReviewStatus reviewStatus) {
        Mono<Farm> farmMono;
        Mono<Review> reviewMono;

        if (reviewStatus == ReviewStatus.CREATE) {
            farmMono = farmRepository.findById(review.getId().getFarmId());
            reviewMono = Mono.just(review);
        } else {
            reviewMono = reviewRepository.findById(review.getId());
            farmMono = reviewMono.map(Review::getFarm);
        }

        return Mono.zip(reviewMono, farmMono)
                .map(tuple -> {
                    Review rev = tuple.getT1();
                    Farm farm = tuple.getT2();

                    int reviewCount = farm.getReviewCount();
                    int newRating;

                    switch (reviewStatus) {
                        case CREATE:
                            reviewCount++;
                            newRating = ((farm.getRating() * reviewCount) + rev.getStars());
                            break;
                        case UPDATE:
                            newRating = ((farm.getRating() * reviewCount) - rev.getStars() + review.getStars());
                            break;
                        case DELETE:
                            if (reviewCount == 1) {
                                newRating = 0;
                                reviewCount = 0;
                            } else {
                                reviewCount--;
                                newRating = ((farm.getRating() * reviewCount) - rev.getStars());
                            }
                            break;
                        default:
                            newRating = farm.getRating();
                            break;
                    }

                    if (reviewCount != 0) {
                        newRating /= reviewCount;
                    }

                    farm.setRating(newRating);
                    farm.setReviewCount(reviewCount);
                    return tuple;
                })
                .flatMap(t -> Mono.zip(farmRepository.save(t.getT2()), reviewRepository.save(t.getT1())))
                .map(Tuple2::getT2);
    }
}