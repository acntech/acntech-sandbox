package no.acntech.sandbox.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import no.acntech.sandbox.model.MessageDto;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class MessageDtoJsonSerializer extends StdSerializer<MessageDto> {

    protected MessageDtoJsonSerializer() {
        super(MessageDto.class);
    }

    @Override
    public void serialize(final MessageDto value, final JsonGenerator generator, final SerializerProvider provider) throws IOException {
        generator.writeString(value.text());
    }
}
