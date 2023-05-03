package no.acntech.sandbox.model;

import jakarta.validation.constraints.NotBlank;

public class CreateFooDto {

    @NotBlank
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
