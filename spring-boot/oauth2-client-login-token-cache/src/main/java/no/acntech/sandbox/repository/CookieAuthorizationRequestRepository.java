package no.acntech.sandbox.repository;

import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.util.Assert;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;

import no.acntech.sandbox.serialize.Base64Deserializer;
import no.acntech.sandbox.serialize.Base64Serializer;

public class CookieAuthorizationRequestRepository implements AuthorizationRequestRepository<OAuth2AuthorizationRequest> {

    private static final String AUTHORIZATION_REQUEST_COOKIE_NAME = "oidc_authorization_request";
    private static final int AUTHORIZATION_REQUEST_COOKIE_EXPIRE_SECONDS = 180;

    @Override
    public OAuth2AuthorizationRequest loadAuthorizationRequest(final HttpServletRequest request) {
        Assert.notNull(request, "HttpServletRequest cannot be null");
        var cookies = request.getCookies();
        if (cookies == null) {
            return null;
        } else {
            return Arrays.stream(cookies)
                    .filter(cookie -> cookie.getName().equals(AUTHORIZATION_REQUEST_COOKIE_NAME))
                    .map(Cookie::getValue)
                    .findFirst()
                    .map(value -> Base64Deserializer.deserialize(value, OAuth2AuthorizationRequest.class))
                    .orElse(null);
        }
    }

    @Override
    public void saveAuthorizationRequest(final OAuth2AuthorizationRequest authorizationRequest,
                                         final HttpServletRequest request,
                                         final HttpServletResponse response) {
        Assert.notNull(request, "HttpServletRequest cannot be null");
        Assert.notNull(response, "HttpServletResponse cannot be null");
        if (authorizationRequest == null) {
            removeAuthorizationRequest(request, response);
        } else {
            String cookieValue = Base64Serializer.serialize(authorizationRequest);
            var cookie = new Cookie(AUTHORIZATION_REQUEST_COOKIE_NAME, cookieValue);
            cookie.setMaxAge(AUTHORIZATION_REQUEST_COOKIE_EXPIRE_SECONDS);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
    }

    @Override
    public OAuth2AuthorizationRequest removeAuthorizationRequest(final HttpServletRequest request,
                                                                 final HttpServletResponse response) {
        Assert.notNull(request, "HttpServletRequest cannot be null");
        Assert.notNull(response, "HttpServletResponse cannot be null");
        var cookie = new Cookie(AUTHORIZATION_REQUEST_COOKIE_NAME, null);
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        return this.loadAuthorizationRequest(request);
    }
}
