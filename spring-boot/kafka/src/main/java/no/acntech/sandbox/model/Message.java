package no.acntech.sandbox.model;

import javax.validation.constraints.NotBlank;

public class Message {

    @NotBlank
    private String title;
    @NotBlank
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "{ \"title\": \"" + title + "\" \"description\": \"" + description + "\" }";
    }
}
