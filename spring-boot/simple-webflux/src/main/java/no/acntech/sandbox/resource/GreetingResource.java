package no.acntech.sandbox.resource;

import no.acntech.sandbox.domain.Greeting;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequestMapping(path = "greetings")
@RestController
public class GreetingResource {

    @GetMapping
    public Mono<ResponseEntity<Greeting>> get(@RequestParam(name = "name", defaultValue = "Nobody") String name) {
        return Mono.just(ResponseEntity.ok(new Greeting("Hello " + name + "!")));
    }
}
