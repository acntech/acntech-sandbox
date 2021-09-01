package no.acntech.sandbox.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.UUID;

import no.acntech.sandbox.model.Message;

@Service
public class ProducerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerService.class);
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public ProducerService(final KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produce(@Valid final Message message) {
        final var key = UUID.randomUUID().toString();
        LOGGER.info("Produced Kafka message with key {}", key);
        kafkaTemplate.send("acntech.sandbox.test.data", key, message);
    }
}
