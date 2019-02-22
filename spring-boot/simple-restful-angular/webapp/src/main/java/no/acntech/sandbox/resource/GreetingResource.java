package no.acntech.sandbox.resource;

import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import no.acntech.sandbox.domain.Greeting;
import no.acntech.sandbox.util.ArrayNonBlockingQueue;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RequestMapping(value = "/api/greetings")
@RestController
public class GreetingResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingResource.class);
    private static final Queue<Greeting> PAST_GREETINGS = new ArrayNonBlockingQueue<>(100);

    @GetMapping(value = "/{name}")
    public Greeting get(@PathVariable(value = "name") String name) {
        LOGGER.debug("Get operation called");
        Greeting greeting = new Greeting("Hello " + name + "!");
        PAST_GREETINGS.add(greeting);
        return greeting;
    }

    @GetMapping
    public Iterable<Greeting> find() {
        LOGGER.debug("Find operation called");
        return PAST_GREETINGS;
    }
}
