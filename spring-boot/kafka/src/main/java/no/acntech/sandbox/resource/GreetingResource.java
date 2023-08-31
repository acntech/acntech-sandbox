package no.acntech.sandbox.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import no.acntech.sandbox.model.GreetingDto;
import no.acntech.sandbox.producer.KafkaProducer;

@RequestMapping(path = "/api/greetings")
@RestController
public class GreetingResource {

    private final KafkaProducer kafkaProducer;

    public GreetingResource(final KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping
    public ResponseEntity<GreetingDto> get(@RequestParam(name = "name", defaultValue = "Nobody") String name) {
        kafkaProducer.produce(new GreetingDto("Hello " + name + "!"));
        return ResponseEntity.ok().build();
    }
}
