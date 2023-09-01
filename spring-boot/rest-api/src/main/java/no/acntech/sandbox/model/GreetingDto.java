package no.acntech.sandbox.model;

import jakarta.validation.constraints.NotBlank;

public record GreetingDto(@NotBlank String message) {
}
