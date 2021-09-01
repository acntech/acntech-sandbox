package no.acntech.sandbox.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import no.acntech.sandbox.model.Message;
import no.acntech.sandbox.service.ProducerService;

@RequestMapping(path = "/api/producer")
@RestController
public class ProducerResource {

    private final ProducerService producerService;

    public ProducerResource(final ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping
    public ResponseEntity<Void> post(@RequestBody final Message message) {
        producerService.produce(message);
        return ResponseEntity.ok().build();
    }
}
