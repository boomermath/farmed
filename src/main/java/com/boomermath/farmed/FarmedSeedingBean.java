package com.boomermath.farmed;

import com.boomermath.farmed.farm.review.ReviewRepository;
import io.micronaut.runtime.event.ApplicationStartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;

import javax.sql.DataSource;
import java.sql.SQLException;

@Singleton
@RequiredArgsConstructor
@Slf4j
public class FarmedSeedingBean {
    private final ReviewRepository reviewRepository;
//    private final DSLContext dsl;
    private final DataSource dataSource;
    @EventListener
    public void appStart(ApplicationStartupEvent event) throws SQLException {
        log.info(dataSource.getConnection().getCatalog());
//        Review review = Review
//                .builder()
//                .id(
//                        ReviewId.builder()
//                                .farmId(UUID.fromString("3c42cd80-f048-412b-be42-5d5095a4a1a8"))
//                                .userId(UUID.fromString("edf7bb32-e0bc-4e84-99a9-d0d02dfaea36"))
//                                .build()
//                )
//                .stars(3)
//                .text("hi")
//                .build();
//
//        review = reviewRepository.save(review).block();
//
//        assert review != null;
//        log.info(review.toString());
    }
}
