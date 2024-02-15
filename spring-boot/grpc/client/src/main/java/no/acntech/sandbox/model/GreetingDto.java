package no.acntech.sandbox.model;

public record GreetingDto(String message) {
    @Override
    public String toString() {
        return message;
    }
}
