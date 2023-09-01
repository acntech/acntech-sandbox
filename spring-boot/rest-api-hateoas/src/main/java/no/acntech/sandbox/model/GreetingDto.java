package no.acntech.sandbox.model;

import org.springframework.hateoas.RepresentationModel;

public class GreetingDto extends RepresentationModel<GreetingDto> {

    private final String message;

    public GreetingDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
