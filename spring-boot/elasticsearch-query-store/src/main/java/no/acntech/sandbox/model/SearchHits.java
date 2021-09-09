package no.acntech.sandbox.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class SearchHits<T> {

    @NotNull
    @Valid
    @JsonProperty("total")
    private SearchPageable pageable;
    @NotNull
    @JsonProperty("hits")
    private List<SearchHit<T>> hits;

    public SearchPageable getPageable() {
        return pageable;
    }

    public List<SearchHit<T>> getHits() {
        return hits;
    }
}
