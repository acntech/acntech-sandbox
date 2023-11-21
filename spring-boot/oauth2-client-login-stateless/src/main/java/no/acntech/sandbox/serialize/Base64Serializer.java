package no.acntech.sandbox.serialize;

import org.springframework.util.Assert;
import org.springframework.util.SerializationUtils;

import java.util.Base64;

public class Base64Serializer {

    public static String serialize(final Object object) {
        Assert.notNull(object, "Object cannot be null");
        return Base64.getUrlEncoder().encodeToString(SerializationUtils.serialize(object));
    }
}
