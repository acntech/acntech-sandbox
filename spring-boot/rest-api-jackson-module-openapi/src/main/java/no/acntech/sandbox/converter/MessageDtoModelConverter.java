package no.acntech.sandbox.converter;

import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverterContext;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import no.acntech.sandbox.model.MessageDto;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class MessageDtoModelConverter implements ModelConverter {

    @Override
    public Schema<?> resolve(final AnnotatedType type,
                             final ModelConverterContext context,
                             final Iterator<ModelConverter> chain) {
        if (type.isSchemaProperty()) {
            final var javaType = Json.mapper().constructType(type.getType());
            if (javaType != null) {
                if (MessageDto.class.isAssignableFrom(javaType.getRawClass())) {
                    return new StringSchema();
                }
            }
        }
        if (chain.hasNext()) {
            return chain.next().resolve(type, context, chain);
        } else {
            return null;
        }
    }
}
