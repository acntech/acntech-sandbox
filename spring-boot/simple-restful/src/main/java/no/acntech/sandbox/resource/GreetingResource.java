package no.acntech.sandbox.resource;

import no.acntech.sandbox.domain.Greeting;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RequestMapping(value = "/greetings")
@RestController
public class GreetingResource {

    @RequestMapping(value = "/{name}", method = GET)
    public Greeting get(@PathVariable(value = "name") String name) {
        return new Greeting("Hello " + name + "!");
    }
}
