package no.acntech.sandbox.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

import no.acntech.sandbox.model.QueryDto;
import no.acntech.sandbox.model.QueryEntity;

@Component
public class QueryEntityToQueryDtoConverter implements Converter<QueryEntity, QueryDto> {

    private static final TypeReference<Map<String, Object>> MAP_TYPE_REFERENCE = new TypeReference<>() {
    };
    private final ObjectMapper objectMapper;

    public QueryEntityToQueryDtoConverter(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @NonNull
    @Override
    public QueryDto convert(@NonNull final QueryEntity source) {
        try {
            final var query = objectMapper.readValue(source.getQuery(), MAP_TYPE_REFERENCE);
            return QueryDto.builder()
                    .id(source.getId())
                    .name(source.getName())
                    .query(query)
                    .build();
        } catch (IOException e) {
            throw new IllegalArgumentException("Query is not correctly formatted JSON", e);
        }
    }
}
