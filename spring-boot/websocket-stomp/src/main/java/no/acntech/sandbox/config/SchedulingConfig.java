package no.acntech.sandbox.config;

import no.acntech.sandbox.model.Greeting;
import no.acntech.sandbox.service.GreetingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@EnableScheduling
@Configuration
public class SchedulingConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulingConfig.class);
    private final GreetingService greetingService;

    public SchedulingConfig(final GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @Scheduled(fixedRateString = "PT10S")
    public void scheduleFixedRateTask() {
        final var timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        LOGGER.debug("Sending STOMP message at {}", timestamp);
        greetingService.sendGreeting(new Greeting("Scheduled hello at " + timestamp));
    }
}
