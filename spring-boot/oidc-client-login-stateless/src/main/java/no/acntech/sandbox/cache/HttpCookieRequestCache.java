package no.acntech.sandbox.cache;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.PortResolver;
import org.springframework.security.web.PortResolverImpl;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.security.web.savedrequest.SimpleSavedRequest;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.acntech.sandbox.repository.HttpCookieOAuth2AuthorizationRequestRepository;
import no.acntech.sandbox.resolver.CookieResolver;

public class HttpCookieRequestCache implements RequestCache {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpCookieOAuth2AuthorizationRequestRepository.class);
    private static final RequestMatcher LOGIN_REQUEST_MATCHER = new AntPathRequestMatcher("/**/login/*");
    private static final PortResolver PORT_RESOLVER = new PortResolverImpl();
    private static final String REDIRECT_URI_PARAM_NAME = "redirect_uri";
    private static final CookieResolver SAVED_REQUEST_COOKIE_RESOLVER = CookieResolver.savedRequestCookieResolver();
    private static final CookieResolver REDIRECT_URI_COOKIE_RESOLVER = CookieResolver.redirectUriCookieResolver();

    @Override
    public void saveRequest(final HttpServletRequest request,
                            final HttpServletResponse response) {
        if (LOGIN_REQUEST_MATCHER.matches(request)) {
            String existingCookieValue = SAVED_REQUEST_COOKIE_RESOLVER.readCookie(request);
            if (StringUtils.isBlank(existingCookieValue)) {
                SavedRequest savedRequest = new DefaultSavedRequest(request, PORT_RESOLVER);
                String cookieValue = CookieResolver.serialize(savedRequest.getRedirectUrl());
                SAVED_REQUEST_COOKIE_RESOLVER.addCookie(response, cookieValue);
            }

            String redirectUri = request.getParameter(REDIRECT_URI_PARAM_NAME);
            if (StringUtils.isNotBlank(redirectUri)) {
                String cookieValue = CookieResolver.serialize(redirectUri);
                //REDIRECT_URI_COOKIE_RESOLVER.addCookie(response, cookieValue);
            }
        }
    }

    @Override
    public SavedRequest getRequest(final HttpServletRequest request,
                                   final HttpServletResponse response) {
        String cookieValue = SAVED_REQUEST_COOKIE_RESOLVER.readCookie(request);
        if (StringUtils.isNotBlank(cookieValue)) {
            String redirectUrl = CookieResolver.deserialize(cookieValue, String.class);
            return new SimpleSavedRequest(redirectUrl);
        } else {
            return null;
        }
    }

    @Override
    public void removeRequest(final HttpServletRequest request,
                              final HttpServletResponse response) {
        //SAVED_REQUEST_COOKIE_RESOLVER.removeCookie(response);
    }

    @Override
    public HttpServletRequest getMatchingRequest(final HttpServletRequest request,
                                                 final HttpServletResponse response) {
        return null;
    }
}
