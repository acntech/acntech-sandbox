package no.acntech.sandbox.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record GreetingDto(@Valid @NotNull MessageDto message) {
}
