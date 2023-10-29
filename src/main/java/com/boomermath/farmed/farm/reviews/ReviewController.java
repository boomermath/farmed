package com.boomermath.farmed.farm.reviews;

import java.util.Optional;
import java.util.UUID;

import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Controller("/farm/")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewRepository reviewRepository;
    
    @Get("/{farmId}/reviews")
    public Mono<Page<Review>> getReviews(@PathVariable("farmId") UUID farmId, @QueryValue Optional<Integer> page, @QueryValue Optional<Integer> size){
        return reviewRepository.findByFarmIdOrderByUpdateAtDesc(farmId, Pageable.from(page.orElse(0), size.orElse(10)));
    }

    @Post("/{farmId}/reviews/{}")
    public Mono<Review> postReview() {

    }
}
