package no.acntech.sandbox.model;

import javax.validation.constraints.NotNull;
import java.util.Map;

public class UpdateQueryDto {

    @NotNull
    private Map<String, Object> query;

    public Map<String, Object> getQuery() {
        return query;
    }
}
