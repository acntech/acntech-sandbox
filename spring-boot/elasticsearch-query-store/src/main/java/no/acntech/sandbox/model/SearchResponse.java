package no.acntech.sandbox.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class SearchResponse<T> {

    @NotNull
    @Valid
    @JsonProperty("hits")
    private SearchHits<T> hits;

    public SearchHits<T> getHits() {
        return hits;
    }
}
