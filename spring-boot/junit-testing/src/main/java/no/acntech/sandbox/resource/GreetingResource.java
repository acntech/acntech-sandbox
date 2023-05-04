package no.acntech.sandbox.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import no.acntech.sandbox.model.Greeting;
import no.acntech.sandbox.service.GreetingService;

@RequestMapping(path = "/api")
@RestController
public class GreetingResource {

    private final GreetingService greetingService;

    public GreetingResource(final GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping(path = "/local-greetings/{name}")
    public Greeting getLocal(@PathVariable(value = "name") String name) {
        return greetingService.getLocalGreeting(name);
    }

    @GetMapping(path = "/remote-greetings")
    public List<Greeting> getRemote(@RequestParam(name = "count", defaultValue = "1") final int count) {
        return greetingService.getRemoteGreeting(count);
    }
}
