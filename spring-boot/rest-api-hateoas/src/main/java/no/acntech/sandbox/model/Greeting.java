package no.acntech.sandbox.model;

import org.springframework.hateoas.RepresentationModel;

public class Greeting extends RepresentationModel {

    private String message;

    public Greeting() {
    }

    public Greeting(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
