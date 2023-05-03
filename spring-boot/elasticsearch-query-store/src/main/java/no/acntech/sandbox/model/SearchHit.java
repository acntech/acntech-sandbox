package no.acntech.sandbox.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SearchHit<T> {

    @NotBlank
    @JsonProperty("_index")
    private String index;
    @NotBlank
    @JsonProperty("_id")
    private String id;
    @NotNull
    @JsonProperty("_source")
    private T source;

    public String getIndex() {
        return index;
    }

    public String getId() {
        return id;
    }

    public T getSource() {
        return source;
    }
}
