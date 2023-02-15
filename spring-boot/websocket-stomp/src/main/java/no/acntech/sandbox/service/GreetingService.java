package no.acntech.sandbox.service;

import jakarta.validation.Valid;
import no.acntech.sandbox.model.Greeting;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    private final SimpMessagingTemplate messagingTemplate;

    public GreetingService(final SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendGreeting(@Valid Greeting greeting) {
        messagingTemplate.convertAndSend("/topic/greetings", greeting);
    }
}
