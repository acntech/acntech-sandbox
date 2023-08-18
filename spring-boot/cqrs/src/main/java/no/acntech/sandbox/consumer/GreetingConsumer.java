package no.acntech.sandbox.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import no.acntech.sandbox.model.GreetingEntity;
import no.acntech.sandbox.model.GreetingEvent;
import no.acntech.sandbox.repository.GreetingRepository;

@Service
public class GreetingConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingConsumer.class);
    private final GreetingRepository greetingRepository;

    public GreetingConsumer(final GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    @KafkaListener(topics = "acntech.sandbox.greetings")
    public void consume(final ConsumerRecord<String, GreetingEvent> record) {
        final var key = record.key();
        final var greetingEvent = record.value();
        LOGGER.info("Consumed greetingEvent event with key {}", key);
        final var greetingEntity = new GreetingEntity(greetingEvent.message());
        greetingRepository.save(greetingEntity);
    }
}
