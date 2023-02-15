package no.acntech.sandbox.repository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import no.acntech.sandbox.resolver.CookieResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InMemorySecurityContextRepository implements SecurityContextRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(InMemorySecurityContextRepository.class);
    private static final CookieResolver SESSION_COOKIE_RESOLVER = CookieResolver.sessionCookieResolver();
    private static final Map<String, SecurityContext> SECURITY_CONTEXT_STORE = new HashMap<>();

    @Override
    public SecurityContext loadContext(final HttpRequestResponseHolder requestResponseHolder) {
        HttpServletRequest request = requestResponseHolder.getRequest();
        String sessionId = SESSION_COOKIE_RESOLVER.readCookie(request);
        if (sessionId != null) {
            SecurityContext securityContext = SECURITY_CONTEXT_STORE.get(sessionId);
            if (securityContext != null) {
                LOGGER.debug("Load SecurityContext from store. Found SecurityContext for session id {} ( request to {} )", sessionId, request.getServletPath());
                return securityContext;
            } else {
                LOGGER.debug("Load SecurityContext from store. No SecurityContext found for session id {}. Creating empty SecurityContext ( request to {} )", sessionId, request.getServletPath());
            }
        } else {
            sessionId = UUID.randomUUID().toString();
            SESSION_COOKIE_RESOLVER.addCookie(requestResponseHolder.getResponse(), sessionId);
            LOGGER.debug("Load SecurityContext from store. No session id found. Generating new session id {}, Creating empty SecurityContext ( request to {} )", sessionId, request.getServletPath());
        }
        return SecurityContextHolder.createEmptyContext();
    }

    @Override
    public void saveContext(final SecurityContext context,
                            final HttpServletRequest request,
                            final HttpServletResponse response) {
        String sessionId = SESSION_COOKIE_RESOLVER.readCookie(request);
        if (sessionId != null) {
            LOGGER.debug("Save SecurityContext to store. Using exiting session id {} ( request to {} )", sessionId, request.getServletPath());
        } else {
            sessionId = UUID.randomUUID().toString();
            LOGGER.debug("Save SecurityContext to store. Generating new session id {} ( request to {} )", sessionId, request.getServletPath());
            SESSION_COOKIE_RESOLVER.addCookie(response, sessionId);
        }
        SECURITY_CONTEXT_STORE.put(sessionId, context);
    }

    @Override
    public boolean containsContext(final HttpServletRequest request) {
        String sessionId = SESSION_COOKIE_RESOLVER.readCookie(request);
        if (sessionId != null) {
            boolean containsContext = SECURITY_CONTEXT_STORE.containsKey(sessionId);
            LOGGER.debug("Check if store contains SecurityContext. Using exiting session id {} with result '{}' ( request to {} )", sessionId, containsContext, request.getServletPath());
            return containsContext;
        } else {
            LOGGER.debug("Check if store contains SecurityContext. No session id found ( request to {} )", request.getServletPath());
            return false;
        }
    }
}
