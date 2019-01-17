package no.acntech.sandbox.service;

import no.acntech.sandbox.domain.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GreetingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingService.class);
    private static final Map<String, String> GREETINGS = new HashMap<>();

    static {
        GREETINGS.put("john", "Hello John! :)");
        GREETINGS.put("jane", "Hi Jane! :)");
        GREETINGS.put("jim", "Howdy Jim! :)");
        GREETINGS.put("jill", "Hello Jill! :)");
    }

    @Cacheable("greetings")
    public Greeting getGreeting(String name) {
        LOGGER.info("Getting greeting for name {}", name);
        String greeting = GREETINGS.getOrDefault(name, "Name not found :(");
        return new Greeting(greeting);
    }
}
