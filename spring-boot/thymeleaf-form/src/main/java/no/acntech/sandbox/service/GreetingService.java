package no.acntech.sandbox.service;

import org.springframework.stereotype.Service;

import jakarta.validation.constraints.NotBlank;

import no.acntech.sandbox.model.GreetingDto;

@Service
public class GreetingService {

    public GreetingDto getGreeting(@NotBlank final String name) {
        return new GreetingDto("Hello " + name + "!");
    }
}
