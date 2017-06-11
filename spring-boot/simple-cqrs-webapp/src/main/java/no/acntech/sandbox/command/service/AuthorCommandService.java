package no.acntech.sandbox.command.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.fasterxml.jackson.databind.ObjectMapper;

import no.acntech.sandbox.entity.Author;

@Service
public class AuthorCommandService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorCommandService.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public AuthorCommandService(final KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void createAuthor(Author author) {
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
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("authors", record);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Record published successfully: {}", result.toString());
                }
            }

            @Override
            public void onFailure(Throwable e) {
                LOGGER.error("Error occurred while publishing record", e);
            }
        });
    }
}
