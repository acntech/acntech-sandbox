package no.acntech.sandbox.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Valid
public class CreateFoo {

    @NotBlank
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
