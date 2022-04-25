package no.acntech.sandbox.resource;

import no.acntech.sandbox.model.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;

@RestController
public class GreetingResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingResource.class);

    @GetMapping("/greetings")
    public SseEmitter greeting() {
        final var connectedTimestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        LOGGER.debug("Connection to SSE at {}", connectedTimestamp);
        var sseEmitter = new SseEmitter(Long.MAX_VALUE);
        sseEmitter.onCompletion(() -> LOGGER.info("SseEmitter is completed"));
        sseEmitter.onTimeout(() -> LOGGER.info("SseEmitter is timed out"));
        sseEmitter.onError((e) -> LOGGER.info("SseEmitter got error:", e));
        var executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            try {
                for (int i = 1; true; i++) {
                    sendEvent(sseEmitter, i);
                    Thread.sleep(Duration.ofSeconds(10).toMillis());
                }
            } catch (Exception e) {
                LOGGER.info("SseEmitter got error:", e);
                sseEmitter.completeWithError(e);
            }
        });
        return sseEmitter;
    }

    private void sendEvent(SseEmitter sseEmitter, int count) {
        try {
            final var messageTimestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            LOGGER.debug("Sending SSE message at {}", messageTimestamp);
            var event = SseEmitter.event()
                    .id(String.valueOf(count))
                    //.name("event") // If event name is specified then the event listener must register for this name
                    .data(new Greeting("Scheduled hello at " + messageTimestamp), MediaType.APPLICATION_JSON);
            sseEmitter.send(event);
        } catch (IOException e) {
            LOGGER.error("An error occurred while send event", e);
        }
    }
}
