package no.acntech.sandbox.serialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;
import no.acntech.sandbox.model.NameDto;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class NameDtoJsonDeserializer extends StdDeserializer<NameDto> {

    public NameDtoJsonDeserializer() {
        super(NameDto.class);
    }

    @Override
    public NameDto deserialize(final JsonParser parser, final DeserializationContext context) throws IOException {
        final var treeNode = parser.getCodec().readTree(parser);
        final var nameNode = treeNode.get("name");
        if (nameNode instanceof TextNode textNode) {
            return NameDto.create(textNode.textValue());
        }
        return NameDto.create(null);
    }
}
