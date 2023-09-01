package no.acntech.sandbox.resource;

import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import no.acntech.sandbox.model.GreetingDto;
import no.acntech.sandbox.util.ArrayNonBlockingQueue;

@RequestMapping(value = "/api/greetings")
@RestController
public class GreetingResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingResource.class);
    private static final Queue<GreetingDto> PAST_GREETINGS = new ArrayNonBlockingQueue<>(100);

    @GetMapping(value = "/{name}")
    public ResponseEntity<GreetingDto> get(@PathVariable(name = "name") String name) {
        LOGGER.debug("Get operation called");
        GreetingDto greeting = new GreetingDto("Hello " + name + "!");
        PAST_GREETINGS.add(greeting);
        return ResponseEntity.ok(greeting);
    }

    @GetMapping
    public Iterable<GreetingDto> find() {
        LOGGER.debug("Find operation called");
        return PAST_GREETINGS;
    }
}
