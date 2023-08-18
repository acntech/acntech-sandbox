package no.acntech.sandbox.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

import no.acntech.sandbox.model.GreetingDto;
import no.acntech.sandbox.service.GreetingQueryService;

@RequestMapping(path = "/api/greetings")
@RestController
public class GreetingResource {

    private final GreetingQueryService queryService;

    public GreetingResource(final GreetingQueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping
    public ResponseEntity<List<GreetingDto>> find(@RequestParam("since") Long since) {
        final var greetings = queryService.findGreetingsSince(Instant.ofEpochMilli(since));
        return ResponseEntity.ok(greetings);
    }
}
