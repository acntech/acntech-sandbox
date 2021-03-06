package no.acntech.sandbox.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Greeting implements Serializable {

    private static final long serialVersionUID = 8378114743856635423L;

    private String greeting;
    private LocalDateTime timestamp;

    public Greeting(String greeting) {
        this.greeting = greeting;
        this.timestamp = LocalDateTime.now();
    }

    public String getGreeting() {
        return greeting;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
