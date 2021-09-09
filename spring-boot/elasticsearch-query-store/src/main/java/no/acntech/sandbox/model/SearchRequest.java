package no.acntech.sandbox.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchRequest<T> {

    @JsonProperty("track_total_hits")
    private Boolean trackTotalHits;
    @JsonProperty("from")
    private Long from;
    @JsonProperty("size")
    private Integer size;
    @JsonProperty("query")
    private T query;

    private SearchRequest() {
    }

    public Boolean getTrackTotalHits() {
        return trackTotalHits;
    }

    public Long getFrom() {
        return from;
    }

    public Integer getSize() {
        return size;
    }

    public T getQuery() {
        return query;
    }

    public static <T> Builder<T> builder() {
        return new Builder<T>();
    }

    public static final class Builder<T> {

        private Boolean trackTotalHits;
        private Long from;
        private Integer size;
        private T query;

        private Builder() {
        }

        public Builder<T> trackTotalHits(Boolean trackTotalHits) {
            this.trackTotalHits = trackTotalHits;
            return this;
        }

        public Builder<T> from(Long from) {
            this.from = from;
            return this;
        }

        public Builder<T> size(Integer size) {
            this.size = size;
            return this;
        }

        public Builder<T> query(T query) {
            this.query = query;
            return this;
        }

        public SearchRequest<T> build() {
            final var searchRequest = new SearchRequest<T>();
            searchRequest.trackTotalHits = this.trackTotalHits;
            searchRequest.from = this.from;
            searchRequest.size = this.size;
            searchRequest.query = this.query;
            return searchRequest;
        }
    }
}
