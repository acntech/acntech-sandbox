package no.acntech.sandbox.model;

import javax.validation.constraints.NotBlank;

public class GreetingFormData {

    @NotBlank
    private String firstName;

    public String getFirstName() {
        return firstName;
    }
}
