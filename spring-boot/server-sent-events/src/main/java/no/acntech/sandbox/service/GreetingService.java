package no.acntech.sandbox.service;

import no.acntech.sandbox.model.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Service
public class GreetingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingService.class);

    public Greeting getGreeting() {
        if ((new Random().nextInt(100) + 1) < 10) { // Produce message 10% of the time
            var timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            return new Greeting("Scheduled hello at " + timestamp);
        } else {
            return null;
        }
    }
}
