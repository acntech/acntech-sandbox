package no.acntech.sandbox.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import no.acntech.sandbox.model.GreetingDto;

@Service
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "acntech.sandbox.greetings")
    public void consume(final ConsumerRecord<String, GreetingDto> record) {
        LOGGER.info("Consumed Kafka message with key: {} and value: {}", record.key(), record.value());
    }
}
