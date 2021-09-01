package no.acntech.sandbox.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import no.acntech.sandbox.model.Message;

@Service
public class ConsumerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerService.class);

    @KafkaListener(topics = "acntech.sandbox.test.data")
    public void consume(final ConsumerRecord<String, Message> record) {
        LOGGER.info("Consumed Kafka message with key {} and value {}", record.key(), record.value());
    }
}
