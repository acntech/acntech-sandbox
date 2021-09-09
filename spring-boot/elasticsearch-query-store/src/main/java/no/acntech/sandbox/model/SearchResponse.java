package no.acntech.sandbox.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class SearchResponse<T> {

    @NotNull
    @Valid
    @JsonProperty("hits")
    private SearchHits<T> hits;

    public SearchHits<T> getHits() {
        return hits;
    }
}
