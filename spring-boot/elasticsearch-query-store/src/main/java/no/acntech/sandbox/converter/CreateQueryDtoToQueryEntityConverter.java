package no.acntech.sandbox.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.io.IOException;

import no.acntech.sandbox.model.CreateQueryDto;
import no.acntech.sandbox.model.QueryEntity;

@Component
public class CreateQueryDtoToQueryEntityConverter implements Converter<CreateQueryDto, QueryEntity> {

    private final ObjectMapper objectMapper;

    public CreateQueryDtoToQueryEntityConverter(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @NonNull
    @Override
    public QueryEntity convert(@NonNull final CreateQueryDto source) {
        try {
            final var query = objectMapper.writeValueAsString(source.getQuery());
            return QueryEntity.builder()
                    .name(source.getName())
                    .query(query)
                    .build();
        } catch (IOException e) {
            throw new IllegalArgumentException("Query is not correctly formatted JSON", e);
        }
    }
}
