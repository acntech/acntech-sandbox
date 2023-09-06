package no.acntech.sandbox.resource;

import no.acntech.sandbox.model.GreetingDto;
import no.acntech.sandbox.model.MessageDto;
import no.acntech.sandbox.model.NameDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/api/greetings")
@RestController
public class GreetingResource {

    @GetMapping
    public ResponseEntity<GreetingDto> get(@RequestParam(name = "name", defaultValue = "Nobody") String name) {
        return ResponseEntity.ok(new GreetingDto(new MessageDto("Hello " + name + "!")));
    }

    @PostMapping
    public ResponseEntity<GreetingDto> post(@RequestBody NameDto nameDto) {
        return ResponseEntity.ok(new GreetingDto(new MessageDto("Hello " + nameDto.getName() + "!")));
    }
}
