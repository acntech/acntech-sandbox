package no.acntech.sandbox.resolver;

import org.springframework.util.Assert;
import org.springframework.util.SerializationUtils;
import org.springframework.web.util.CookieGenerator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Base64;

public class CookieResolver extends CookieGenerator {

    private static final String AUTHORIZATION_REQUEST_COOKIE_NAME = "oidc_authorization_request";
    private static final int AUTHORIZATION_REQUEST_COOKIE_EXPIRE_SECONDS = 180;
    private static final String REDIRECT_URI_COOKIE_NAME = "oidc_redirect_uri";
    private static final int REDIRECT_URI_COOKIE_EXPIRE_SECONDS = 180;
    private static final String SAVED_REQUEST_COOKIE_NAME = "oidc_saved_request";
    private static final int SAVED_REQUEST_COOKIE_EXPIRE_SECONDS = 180;
    private static final String SESSION_COOKIE_NAME = "oidc_session";
    private static final int SESSION_COOKIE_EXPIRE_SECONDS = -1;

    private CookieResolver(String cookieName, int cookieMaxAge) {
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

    public static CookieResolver authorizationRequestCookieResolver() {
        return new CookieResolver(AUTHORIZATION_REQUEST_COOKIE_NAME, AUTHORIZATION_REQUEST_COOKIE_EXPIRE_SECONDS);
    }

    public static CookieResolver redirectUriCookieResolver() {
        return new CookieResolver(REDIRECT_URI_COOKIE_NAME, REDIRECT_URI_COOKIE_EXPIRE_SECONDS);
    }

    public static CookieResolver savedRequestCookieResolver() {
        return new CookieResolver(SAVED_REQUEST_COOKIE_NAME, SAVED_REQUEST_COOKIE_EXPIRE_SECONDS);
    }

    public static CookieResolver sessionCookieResolver() {
        return new CookieResolver(SESSION_COOKIE_NAME, SESSION_COOKIE_EXPIRE_SECONDS);
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
