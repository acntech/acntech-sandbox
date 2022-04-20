package no.acntech.sandbox.repository;

import no.acntech.sandbox.resolver.CookieResolver;
import no.acntech.sandbox.store.SecurityContextStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class RedisSecurityContextRepository implements SecurityContextRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisSecurityContextRepository.class);
    private static final CookieResolver SESSION_COOKIE_RESOLVER = CookieResolver.sessionCookieResolver();
    private final SecurityContextStore securityContextStore;

    public RedisSecurityContextRepository(final SecurityContextStore securityContextStore) {
        this.securityContextStore = securityContextStore;
    }

    @Override
    public SecurityContext loadContext(final HttpRequestResponseHolder requestResponseHolder) {
        HttpServletRequest request = requestResponseHolder.getRequest();
        String sessionId = SESSION_COOKIE_RESOLVER.readCookie(request);
        if (sessionId != null) {
            SecurityContext securityContext = securityContextStore.loadContext(sessionId);
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
        securityContextStore.saveContext(sessionId, context);
    }

    @Override
    public boolean containsContext(final HttpServletRequest request) {
        String sessionId = SESSION_COOKIE_RESOLVER.readCookie(request);
        if (sessionId != null) {
            boolean containsContext = securityContextStore.containsContext(sessionId);
            LOGGER.debug("Check if store contains SecurityContext. Using exiting session id {} with result '{}' ( request to {} )", sessionId, containsContext, request.getServletPath());
            return containsContext;
        } else {
            LOGGER.debug("Check if store contains SecurityContext. No session id found ( request to {} )", request.getServletPath());
            return false;
        }
    }
}
