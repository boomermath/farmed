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
    protected enum ReviewStatus {
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

    @Transactional
    protected Mono<Review> updateFarmAndReview(Review review, ReviewStatus reviewStatus) {
        Mono<Farm> farmMono;
        Mono<Review> reviewMono;

        if (reviewStatus == ReviewStatus.CREATE) {
            farmMono = farmRepository.findById(review.getId().getFarmId());
            reviewMono = reviewRepository.save(review);
        } else {
            reviewMono = reviewRepository.findById(review.getId());
            farmMono = reviewMono.map(Review::getFarm);
        }

        return Mono.zip(farmMono, reviewMono).map(tuple -> {
            Review currentReview = tuple.getT2();
            Farm farm = tuple.getT1();

            int rCount = farm.getReviewCount();
            double rating = farm.getRating();

            switch (reviewStatus) {
                case CREATE:
                    rating = ((rating * rCount) + rating) / (rCount+1);
                    rCount++;
                    break;
                case UPDATE:
                    rating = ((rating * rCount) + rating - currentReview.getStars()) / rCount;
                    break;
                case DELETE:
                    rating = ((rating * rCount)  - currentReview.getStars()) / (rCount-1);
                    rCount--;
                    break;
                default:
                    break;
            }

            farm.setRating(rating);
            farm.setReviewCount(rCount);
            return tuple;
        }).map(Tuple2::getT2);
    }
}