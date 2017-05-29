package no.acntech.sandbox.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import no.acntech.sandbox.domain.Greeting;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RequestMapping(value = "/api/greetings")
@RestController
public class GreetingResource {

    @RequestMapping(value = "/{name}", method = GET)
    public Greeting get(@PathVariable(value = "name") String name) {
        return new Greeting("Hello " + name + "!");
    }

    @RequestMapping(value = "", method = GET)
    public Iterable<Greeting> find() {
        List<Greeting> greetings = new ArrayList<>();
        greetings.add(new Greeting("Hello John!"));
        greetings.add(new Greeting("Hello Jane!"));
        greetings.add(new Greeting("Hello Jonathan!"));
        return greetings;
    }
}
