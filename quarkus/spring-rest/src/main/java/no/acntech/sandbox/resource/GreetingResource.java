package no.acntech.sandbox.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import no.acntech.sandbox.model.Greeting;

@RequestMapping("greetings")
@RestController
public class GreetingResource {

    @GetMapping
    public Greeting get(@RequestParam("name") String name) {
        return new Greeting("Hello " + (name != null ? name : "Nobody") + "!");
    }
}
