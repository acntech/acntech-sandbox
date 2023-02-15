package no.acntech.sandbox.model;

import jakarta.validation.constraints.NotBlank;

public class Hello {

    @NotBlank
    private String name;

    public String getName() {
        return name;
    }
}
