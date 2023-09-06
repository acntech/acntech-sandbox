package no.acntech.sandbox.service;

import jakarta.validation.constraints.NotBlank;

import no.acntech.sandbox.model.GreetingDto;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public GreetingDto getGreeting(@NotBlank final String name) {
        return new GreetingDto("Hello " + name + "!");
    }
}
