package com.boomermath.farmed.farm.review;

import com.boomermath.farmed.farm.Farm;
import com.boomermath.farmed.farm.FarmRepository;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Singleton
@RequiredArgsConstructor
public class ReviewService {
    private final FarmRepository farmRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Transactional
    public Mono<ReviewDTO> createReview(Review review) {
        return farmRepository.findById(review.getFarmId())
                .map(f -> {
                    int currentReviewCount = f.getReviewCount();

                    f.setRating(
                            ((f.getRating() * currentReviewCount) + review.getStars()) / (currentReviewCount + 1));
                    f.setReviewCount(currentReviewCount + 1);
                    review.setFarm(f);

                    return review;
                })
                .flatMap(r -> Mono.zip(farmRepository.save(r.getFarm()), reviewRepository.save(r)))
                .map(Tuple2::getT2)
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

    public Mono<Void> deleteReview(ReviewIdDTO reviewIdDTO) {
        return reviewRepository.deleteByIdAndFarmIdAndUserId(reviewIdDTO.getId(), reviewIdDTO.getFarmId(), reviewIdDTO.getUserId())
                .switchIfEmpty(Mono.error(new Exception("INVALID_REVIEW")))
                .then();

        /*

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
         */
    }
}
