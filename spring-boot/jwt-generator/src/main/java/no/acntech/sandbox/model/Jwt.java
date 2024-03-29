package no.acntech.sandbox.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@Valid
public class Jwt {

    @NotBlank
    private String jwt;
    private Boolean verified;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }
}
