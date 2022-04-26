package no.acntech.sandbox.resource;

import no.acntech.sandbox.service.GreetingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RestController
public class GreetingResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingResource.class);
    private final GreetingService greetingService;

    public GreetingResource(final GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/greetings")
    public SseEmitter greeting() {
        var connectedTimestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        LOGGER.debug("Connection to SSE at {}", connectedTimestamp);
        var sseEmitter = createSseEmitter();
        var executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            try {
                sendEvent(sseEmitter);
            } catch (Exception e) {
                LOGGER.info("SseEmitter got error:", e);
                sseEmitter.completeWithError(e);
            }
        }, 0, 1, TimeUnit.SECONDS);
        return sseEmitter;
    }

    private void sendEvent(SseEmitter sseEmitter) {
        var greeting = greetingService.getGreeting();
        if (greeting != null) {
            try {
                var timestamp = LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                LOGGER.debug("Sending event at {}", timestamp);
                sseEmitter.send(greeting, MediaType.APPLICATION_JSON);
            } catch (IOException e) {
                LOGGER.error("An error occurred while send event", e);
            }
        }
    }

    private SseEmitter createSseEmitter() {
        var sseEmitter = new SseEmitter(Long.MAX_VALUE);
        sseEmitter.onCompletion(() -> LOGGER.info("SseEmitter is completed"));
        sseEmitter.onTimeout(() -> LOGGER.info("SseEmitter is timed out"));
        sseEmitter.onError((e) -> LOGGER.info("SseEmitter got error:", e));
        return sseEmitter;
    }
}
