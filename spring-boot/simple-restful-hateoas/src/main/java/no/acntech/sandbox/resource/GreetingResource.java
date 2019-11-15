package no.acntech.sandbox.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import no.acntech.sandbox.domain.Greeting;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequestMapping(value = "/greetings")
@RestController
public class GreetingResource {

    @GetMapping(value = "/{name}")
    public Greeting get(@PathVariable(value = "name") String name) {
        Greeting greeting = new Greeting("Hello " + name + "!");
        greeting.add(linkTo(methodOn(GreetingResource.class).get(name)).withSelfRel());
        return greeting;
    }
}
