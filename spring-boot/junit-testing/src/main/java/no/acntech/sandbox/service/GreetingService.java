package no.acntech.sandbox.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import no.acntech.sandbox.model.Greeting;

@Service
public class GreetingService {

    private final RestTemplate restTemplate;
    private final String namesUrl;

    public GreetingService(final RestTemplate restTemplate,
                           @Value("${app.integration.names.url}") String namesUrl) {
        this.restTemplate = restTemplate;
        this.namesUrl = namesUrl;
    }

    public Greeting getLocalGreeting(@Valid final String name) {
        return new Greeting("Hello " + name + "!");
    }

    public List<Greeting> getRemoteGreeting(int count) {
        final var uri = UriComponentsBuilder.fromUriString(namesUrl)
                .build(count);
        final var names = restTemplate.getForObject(uri, String[].class);
        if (names == null || names.length == 0) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return Arrays.stream(names)
                .map(name -> new Greeting("Hello " + name + "!"))
                .collect(Collectors.toList());
    }
}
