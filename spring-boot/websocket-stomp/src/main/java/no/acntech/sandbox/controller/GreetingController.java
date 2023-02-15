package no.acntech.sandbox.controller;

import jakarta.validation.Valid;
import no.acntech.sandbox.model.Greeting;
import no.acntech.sandbox.model.Hello;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class GreetingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingController.class);

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(@Valid Hello hello) {
        final var timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        LOGGER.debug("Sending STOMP message at {}", timestamp);
        return new Greeting("Manual hello to " + hello.getName() + " at " + timestamp);
    }
}
