package no.acntech.sandbox.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Map;

public class QueryDto {

    @NotNull
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private Map<String, Object> query;

    private QueryDto() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<String, Object> getQuery() {
        return query;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private Long id;
        private String name;
        private Map<String, Object> query;

        private Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder query(Map<String, Object> query) {
            this.query = query;
            return this;
        }

        public QueryDto build() {
            QueryDto queryDto = new QueryDto();
            queryDto.id = this.id;
            queryDto.name = this.name;
            queryDto.query = this.query;
            return queryDto;
        }
    }
}
