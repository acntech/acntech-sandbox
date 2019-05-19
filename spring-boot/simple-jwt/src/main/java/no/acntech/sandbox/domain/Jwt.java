package no.acntech.sandbox.domain;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Valid
public class Jwt {

    @NotBlank
    private String jwt;

    public String getJwt() {
        return jwt;
    }
}
