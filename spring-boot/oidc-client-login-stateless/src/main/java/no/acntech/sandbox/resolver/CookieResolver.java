package no.acntech.sandbox.resolver;

import org.springframework.util.Assert;
import org.springframework.util.SerializationUtils;
import org.springframework.web.util.CookieGenerator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Base64;

public class CookieResolver extends CookieGenerator {

    public CookieResolver(String cookieName, int cookieMaxAge) {
        this.setCookieName(cookieName);
        this.setCookieMaxAge(cookieMaxAge);
        this.setCookieHttpOnly(true);
    }

    public String readCookie(final HttpServletRequest request) {
        Assert.notNull(request, "HttpServletRequest cannot be null");
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        } else {
            return Arrays.stream(cookies)
                    .filter(cookie -> cookie.getName().equals(this.getCookieName()))
                    .map(Cookie::getValue)
                    .findFirst()
                    .orElse(null);
        }
    }

    public static String serialize(Object object) {
        return Base64.getUrlEncoder()
                .encodeToString(SerializationUtils.serialize(object));
    }

    public static <T> T deserialize(String value, Class<T> cls) {
        return cls.cast(SerializationUtils.deserialize(
                Base64.getUrlDecoder().decode(value)));
    }
}
