package no.acntech.sandbox.producer;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

import no.acntech.sandbox.model.Greeting;

@Service
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaProducer(final KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produce(@Valid final Greeting message) {
        final var key = UUID.randomUUID().toString();
        LOGGER.info("Produced Kafka message with key {}", key);
        kafkaTemplate.send("acntech.sandbox.test.data", key, message);
    }
}
