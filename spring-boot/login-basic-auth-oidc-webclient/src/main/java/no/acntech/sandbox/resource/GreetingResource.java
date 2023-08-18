package no.acntech.sandbox.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import no.acntech.sandbox.model.GreetingDto;

@RequestMapping(path = "/api/greetings")
@RestController
public class GreetingResource {

    @GetMapping
    public ResponseEntity<GreetingDto> get(@RequestParam(name = "name", defaultValue = "Nobody") String name) {
        return ResponseEntity.ok(new GreetingDto("Hello " + name + "!"));
    }
}
