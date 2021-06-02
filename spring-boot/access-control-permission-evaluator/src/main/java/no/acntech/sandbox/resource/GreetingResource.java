package no.acntech.sandbox.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import no.acntech.sandbox.model.Greeting;

@RequestMapping(path = "greetings")
@RestController
public class GreetingResource {

    @PreAuthorize("hasPermission('READ', 'USER')")
    @GetMapping
    public ResponseEntity<Greeting> get(@RequestParam(name = "name", defaultValue = "Nobody") String name) {
        return ResponseEntity.ok(new Greeting("Hello " + name + "!"));
    }

    @PreAuthorize("hasPermission('WRITE', 'ADMIN')")
    @PostMapping
    public ResponseEntity<Greeting> post(@RequestParam(name = "name", defaultValue = "Nobody") String name) {
        return ResponseEntity.ok(new Greeting("Hello " + name + "!"));
    }
}
