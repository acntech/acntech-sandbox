package no.acntech.sandbox.resource;

import no.acntech.sandbox.model.Greeting;
import no.acntech.sandbox.service.GreetingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class GreetingResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingResource.class);
    private final GreetingService greetingService;

    public GreetingResource(final GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/greetings")
    public ResponseEntity<Greeting> greeting() {
        final var greeting = greetingService.getGreeting();
        final var timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        if (greeting != null) {
            LOGGER.debug("Fetching message at {}", timestamp);
            return ResponseEntity.ok(greeting);
        } else {
            LOGGER.debug("No message to fetch at {}", timestamp);
            return ResponseEntity.noContent().build();
        }
    }
}
