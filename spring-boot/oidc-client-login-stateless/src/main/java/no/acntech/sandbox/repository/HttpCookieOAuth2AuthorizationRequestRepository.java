package no.acntech.sandbox.repository;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import no.acntech.sandbox.resolver.CookieResolver;

public class HttpCookieOAuth2AuthorizationRequestRepository implements AuthorizationRequestRepository<OAuth2AuthorizationRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpCookieOAuth2AuthorizationRequestRepository.class);
    private static final CookieResolver AUTHORIZATION_REQUEST_COOKIE_RESOLVER = CookieResolver.authorizationRequestCookieResolver();

    @Override
    public OAuth2AuthorizationRequest loadAuthorizationRequest(final HttpServletRequest request) {
        Assert.notNull(request, "HttpServletRequest cannot be null");
        String cookieValue = AUTHORIZATION_REQUEST_COOKIE_RESOLVER.readCookie(request);
        OAuth2AuthorizationRequest authorizationRequest = Optional.ofNullable(cookieValue)
                .filter(StringUtils::isNoneBlank)
                .map(this::deserializeCookie)
                .orElse(null);
        if (authorizationRequest == null) {
            LOGGER.debug("No OAuth2AuthorizationRequest found in store ( request to {} )", request.getServletPath());
        } else {
            LOGGER.debug("Found OAuth2AuthorizationRequest in store ( request to {} )", request.getServletPath());
        }
        return authorizationRequest;
    }

    @Override
    public void saveAuthorizationRequest(final OAuth2AuthorizationRequest authorizationRequest,
                                         final HttpServletRequest request,
                                         final HttpServletResponse response) {
        Assert.notNull(request, "HttpServletRequest cannot be null");
        Assert.notNull(response, "HttpServletResponse cannot be null");
        if (authorizationRequest == null) {
            LOGGER.debug("No OAuth2AuthorizationRequest found to store");
            removeAuthorizationRequest(request, response);
        } else {
            LOGGER.debug("Storing OAuth2AuthorizationRequest ( request to {} )", request.getServletPath());
            String cookieValue = CookieResolver.serialize(authorizationRequest);
            AUTHORIZATION_REQUEST_COOKIE_RESOLVER.addCookie(response, cookieValue);
        }
    }

    @Override
    public OAuth2AuthorizationRequest removeAuthorizationRequest(final HttpServletRequest request) {
        return this.loadAuthorizationRequest(request);
    }

    @Override
    public OAuth2AuthorizationRequest removeAuthorizationRequest(final HttpServletRequest request,
                                                                 final HttpServletResponse response) {
        Assert.notNull(request, "HttpServletRequest cannot be null");
        Assert.notNull(response, "HttpServletResponse cannot be null");
        LOGGER.debug("Removing stored OAuth2AuthorizationRequest ( request to {} )", request.getServletPath());
        AUTHORIZATION_REQUEST_COOKIE_RESOLVER.removeCookie(response);
        return this.loadAuthorizationRequest(request);
    }

    private OAuth2AuthorizationRequest deserializeCookie(String value) {
        return CookieResolver.deserialize(value, OAuth2AuthorizationRequest.class);
    }
}
