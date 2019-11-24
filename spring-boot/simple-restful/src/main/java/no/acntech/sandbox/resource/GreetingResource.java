package no.acntech.sandbox.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import no.acntech.sandbox.domain.Greeting;

@RequestMapping(path = "greetings")
@RestController
public class GreetingResource {

    @GetMapping(path = "{name}")
    public Greeting get(@PathVariable(value = "name") String name) {
        return new Greeting("Hello " + name + "!");
    }
}
