package no.acntech.sandbox.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import java.util.UUID;

import no.acntech.sandbox.model.GreetingDto;

@Service
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaProducer(final KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produce(@Valid final GreetingDto greeting) {
        final var key = UUID.randomUUID().toString();
        LOGGER.info("Produced Kafka greeting with key {}", key);
        kafkaTemplate.send("acntech.sandbox.greetings", key, greeting);
    }
}
