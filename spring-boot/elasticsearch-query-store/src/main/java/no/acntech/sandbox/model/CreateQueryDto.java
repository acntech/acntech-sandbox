package no.acntech.sandbox.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

public class CreateQueryDto {

    @NotBlank
    private String name;
    @NotNull
    private Map<String, Object> query;

    public String getName() {
        return name;
    }

    public Map<String, Object> getQuery() {
        return query;
    }
}
