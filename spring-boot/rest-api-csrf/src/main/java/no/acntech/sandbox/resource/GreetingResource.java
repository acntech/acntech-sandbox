package no.acntech.sandbox.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import no.acntech.sandbox.model.Greeting;
import no.acntech.sandbox.model.Name;

@RequestMapping(path = "greetings")
@RestController
public class GreetingResource {

    @GetMapping
    public ResponseEntity<Greeting> get(@RequestParam(name = "name", defaultValue = "Nobody") String name) {
        return ResponseEntity.ok(new Greeting("Hello " + name + "!"));
    }

    @PostMapping
    public ResponseEntity<Greeting> post(@RequestBody Name name) {
        String nullSafeName = StringUtils.hasText(name.getName()) ? name.getName() : "Nobody";
        return ResponseEntity.ok(new Greeting("Hello " + nullSafeName + "!"));
    }
}
