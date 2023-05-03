package no.acntech.sandbox.webapp.model;

import jakarta.validation.constraints.NotBlank;

public class Person {

    @NotBlank
    private String firstName;

    public String getFirstName() {
        return firstName;
    }
}
