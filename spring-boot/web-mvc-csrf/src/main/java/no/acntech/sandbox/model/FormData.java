package no.acntech.sandbox.model;

import jakarta.validation.constraints.NotBlank;

public class FormData {

    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
