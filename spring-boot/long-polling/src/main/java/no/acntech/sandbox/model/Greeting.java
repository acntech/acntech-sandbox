package no.acntech.sandbox.model;

import jakarta.validation.constraints.NotBlank;

public class Greeting {

    @NotBlank
    private String message;

    public Greeting(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
