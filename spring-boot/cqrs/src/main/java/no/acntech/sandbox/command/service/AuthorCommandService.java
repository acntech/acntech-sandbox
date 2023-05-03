package no.acntech.sandbox.command.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import no.acntech.sandbox.model.AuthorEntity;

@Service
public class AuthorCommandService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorCommandService.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public AuthorCommandService(final KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void createAuthor(AuthorEntity author) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String record = mapper.writeValueAsString(author);
            publish(record);
        } catch (IOException e) {
            LOGGER.error("Error occurred while serializing record", e);
        }
    }

    private void publish(String record) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Publishing record: {}", record);
        }
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send("authors", record);
        future.whenComplete((result, exception) -> {
            if (exception == null) {
                LOGGER.debug("Record published successfully: {}", result.toString());
            } else {
                LOGGER.error("Error occurred while publishing record", exception);
            }
        });
    }
}
