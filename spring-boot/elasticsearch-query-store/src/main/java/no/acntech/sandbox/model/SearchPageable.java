package no.acntech.sandbox.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
