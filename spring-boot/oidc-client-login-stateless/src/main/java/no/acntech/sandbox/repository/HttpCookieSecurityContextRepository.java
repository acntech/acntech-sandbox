package no.acntech.sandbox.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.acntech.sandbox.resolver.CookieResolver;

public class HttpCookieSecurityContextRepository implements SecurityContextRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpCookieSecurityContextRepository.class);
    public static final String SESSION_COOKIE_NAME = "oidc_session";
    private static final int SESSION_COOKIE_EXPIRE_SECONDS = -1;
    public static final CookieResolver SESSION_COOKIE_RESOLVER = new CookieResolver(SESSION_COOKIE_NAME, SESSION_COOKIE_EXPIRE_SECONDS);
    private final OAuth2AuthorizedClientService authorizedClientService;

    public HttpCookieSecurityContextRepository(final OAuth2AuthorizedClientService authorizedClientService) {
        this.authorizedClientService = authorizedClientService;
    }

    @Override
    public SecurityContext loadContext(final HttpRequestResponseHolder requestResponseHolder) {
        HttpServletRequest request = requestResponseHolder.getRequest();
        String sessionCookie = SESSION_COOKIE_RESOLVER.readCookie(request);
        if (sessionCookie != null) {
            String[] split = sessionCookie.split(":");
            SecurityContext securityContext = authorizedClientService.loadAuthorizedClient(split[0], split[1]);
            if (securityContext != null) {
                LOGGER.debug("Load SecurityContext. Found SecurityContext for session cookie {} ( request to {} )", "", request.getServletPath());
                return securityContext;
            } else {
                LOGGER.debug("Load SecurityContext. No SecurityContext found for session cookie {}. Creating empty SecurityContext ( request to {} )", "", request.getServletPath());
            }
        } else {
            LOGGER.debug("Load SecurityContext. No session cookie found. Generating new session cookie {}, Creating empty SecurityContext ( request to {} )", "", request.getServletPath());
        }
        return SecurityContextHolder.createEmptyContext();
    }

    @Override
    public void saveContext(final SecurityContext securityContext,
                            final HttpServletRequest request,
                            final HttpServletResponse response) {
        String sessionCookie = SESSION_COOKIE_RESOLVER.readCookie(request);
        if (sessionCookie != null) {
            LOGGER.debug("Save session cookie to store. Using exiting session cookie {} ( request to {} )", sessionCookie, request.getServletPath());
        }

        if (securityContext != null && securityContext.getAuthentication() instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken) securityContext.getAuthentication();
            String clientRegistrationId = authenticationToken.getAuthorizedClientRegistrationId();
            OAuth2User principal = authenticationToken.getPrincipal();
            String cookieValue = String.format("%s:%s", clientRegistrationId, principal.getName());
            SESSION_COOKIE_RESOLVER.addCookie(response, cookieValue);
        } else {
            LOGGER.debug("Save session cookie to store. Generating new session cookie {} ( request to {} )", "", request.getServletPath());
        }
    }

    @Override
    public boolean containsContext(final HttpServletRequest request) {
        String sessionCookie = SESSION_COOKIE_RESOLVER.readCookie(request);
        if (sessionCookie != null) {
            LOGGER.debug("Check if session cookie exists. Found session cookie {} ( request to {} )", sessionCookie, request.getServletPath());
            return true;
        } else {
            LOGGER.debug("Check if session cookie exists. No session cookie found ( request to {} )", request.getServletPath());
            return false;
        }
    }
}
