package no.acntech.sandbox.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import java.util.UUID;

import no.acntech.sandbox.model.GreetingEvent;

@Service
public class GreetingProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingProducer.class);

    private final KafkaTemplate<String, GreetingEvent> kafkaTemplate;

    @Autowired
    public GreetingProducer(final KafkaTemplate<String, GreetingEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(@Valid final GreetingEvent greetingEvent) {
        final var key = UUID.randomUUID().toString();
        LOGGER.info("Produced greetingEvent event with key {}", key);
        kafkaTemplate.send("acntech.sandbox.greetings", key, greetingEvent);
    }
}
