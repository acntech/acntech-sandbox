package no.acntech.sandbox.domain;

import java.time.LocalDateTime;

public class Greeting {

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
