package no.acntech.sandbox.serialize;

import org.springframework.util.Assert;
import org.springframework.util.SerializationUtils;

import java.util.Base64;

public class Base64Deserializer {

    public static <T> T deserialize(String value, Class<T> cls) {
        Assert.notNull(value, "Value cannot be null");
        Assert.notNull(cls, "Class cannot be null");
        return cls.cast(SerializationUtils.deserialize(Base64.getUrlDecoder().decode(value)));
    }
}
