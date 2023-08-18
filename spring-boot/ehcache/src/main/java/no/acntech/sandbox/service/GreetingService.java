package no.acntech.sandbox.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import no.acntech.sandbox.model.GreetingDto;
import no.acntech.sandbox.model.GreetingEntity;

@Service
public class GreetingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingService.class);
    private static final Map<String, GreetingEntity> GREETINGS = new HashMap<>();

    @Cacheable("greetings")
    public GreetingDto getGreeting(final String name) {
        LOGGER.info("Getting greeting for name {}", name);
        final var key = name.toLowerCase();
        final GreetingEntity greetingEntity;
        if (GREETINGS.containsKey(key)) {
            greetingEntity = GREETINGS.get(key);
        } else {
            greetingEntity = new GreetingEntity("Hello " + name + "!", Instant.now());
            GREETINGS.put(key, greetingEntity);
        }
        return new GreetingDto(greetingEntity.message(), greetingEntity.created(), Instant.now());
    }
}
