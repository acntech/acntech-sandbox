package no.acntech.sandbox.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SearchPageable {

    @NotNull
    @JsonProperty("value")
    private Long totalElements;
    @NotBlank
    @JsonProperty("relation")
    private String relation;

    public Long getTotalElements() {
        return totalElements;
    }

    public String getRelation() {
        return relation;
    }
}
