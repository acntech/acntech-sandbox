package no.acntech.sandbox.model;

public class NameDto {

    private final String name;

    private NameDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static NameDto create(String name) {
        return new NameDto(name);
    }
}
