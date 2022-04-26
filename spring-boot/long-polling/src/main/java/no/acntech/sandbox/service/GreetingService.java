package no.acntech.sandbox.service;

import no.acntech.sandbox.model.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class GreetingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingService.class);

    public Greeting getGreeting() {
        try {
            Greeting greeting = null;
            for (int i = 0; greeting == null && i < 10; i++) { // Waits for 10 sec or for when a message is available
                TimeUnit.SECONDS.sleep(1);
                greeting = produceGreeting();
            }
            return greeting;
        } catch (InterruptedException e) {
            return null;
        }
    }

    private Greeting produceGreeting() {
        if ((new Random().nextInt(100) + 1) < 10) { // Produce message 10% of the time
            var timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            return new Greeting("Scheduled hello at " + timestamp);
        } else {
            return null;
        }
    }
}
