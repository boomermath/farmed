package com.boomermath.farmed;

import com.boomermath.farmed.farm.Farm;
import com.boomermath.farmed.farm.FarmRepository;
import com.boomermath.farmed.farm.contact.Contact;
import com.boomermath.farmed.user.User;
import io.micronaut.runtime.event.ApplicationStartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class FarmSeedingBean {
    private final FarmRepository farmRepository;

    @EventListener
    public void start(ApplicationStartupEvent event) {
//        Farm farm = Farm.builder()
//                .name("Union Farmers Market")
//                .contact(
//                        Contact.builder()
//                                .phoneNumber("(704) 519-8095")
//                                .email("boomermath@gmail.com")
//                                .website("https://market.com")
//                                .build()
//                )
//                .build();
//
//        farmRepository.save(farm)
//                .block();
    }
}
