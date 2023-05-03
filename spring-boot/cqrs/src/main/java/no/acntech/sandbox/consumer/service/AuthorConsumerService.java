package no.acntech.sandbox.consumer.service;

import java.io.IOException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import no.acntech.sandbox.model.AuthorEntity;
import no.acntech.sandbox.repository.AuthorRepository;

@Service
public class AuthorConsumerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorConsumerService.class);

    private final AuthorRepository repository;

    @Autowired
    public AuthorConsumerService(final AuthorRepository repository) {
        this.repository = repository;
    }

    public void createAuthor(AuthorEntity author) {
        repository.save(author);
    }

    @KafkaListener(id = "authors", topics = "authors", groupId = "authors")
    public void listen(ConsumerRecord<String, String> record) {
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Consumed new record: {}", record.toString());
            }
            ObjectMapper mapper = new ObjectMapper();
            AuthorEntity author = mapper.readValue(record.value(), AuthorEntity.class);
            createAuthor(author);
        } catch (IOException e) {
            LOGGER.error("Error occurred while deserializing record", e);
        }
    }
}
