package no.acntech.sandbox.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import no.acntech.sandbox.model.GreetingDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequestMapping(value = "/greetings")
@RestController
public class GreetingResource {

    @GetMapping
    public ResponseEntity<GreetingDto> get(@RequestParam(name = "name", defaultValue = "Nobody") String name) {
        final var greeting = new GreetingDto("Hello " + name + "!");
        greeting.add(linkTo(methodOn(GreetingResource.class).get(name)).withSelfRel());
        return ResponseEntity.ok(greeting);
    }
}
