package no.acntech.sandbox.resource;

import no.acntech.sandbox.domain.Greeting;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RequestMapping(value = "/greetings")
@RestController
public class GreetingResource {

    @RequestMapping(value = "/{name}", method = GET)
    public Greeting get(@PathVariable(value = "name") String name) {
        Greeting greeting = new Greeting("Hello " + name + "!");
        greeting.add(linkTo(methodOn(GreetingResource.class).get(name)).withSelfRel());
        return greeting;
    }
}
