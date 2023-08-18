package no.acntech.sandbox.model;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

public record GreetingDto(String message, Instant created, Instant fetched) implements Serializable {
    @Serial
    private static final long serialVersionUID = 8378114743856635423L;
}
