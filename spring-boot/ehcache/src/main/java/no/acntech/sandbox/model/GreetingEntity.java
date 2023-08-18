package no.acntech.sandbox.model;

import java.time.Instant;

public record GreetingEntity(String message, Instant created) {
}
