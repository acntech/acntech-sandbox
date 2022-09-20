package no.acntech.sandbox.repository;

import no.acntech.sandbox.resolver.CookieResolver;
import no.acntech.sandbox.store.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.function.Supplier;

public class InMemorySecurityContextRepository implements SecurityContextRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(InMemorySecurityContextRepository.class);
    private final CookieResolver sessionCookieResolver;
    private final Store<String, SecurityContext> securityContextStore;

    public InMemorySecurityContextRepository(final CookieResolver sessionCookieResolver,
                                             final Store<String, SecurityContext> securityContextStore) {
        this.sessionCookieResolver = sessionCookieResolver;
        this.securityContextStore = securityContextStore;
    }

    @Override
    public SecurityContext loadContext(final HttpRequestResponseHolder requestResponseHolder) {
        return this.loadContext(requestResponseHolder.getRequest()).get();
    }

    @Override
    public Supplier<SecurityContext> loadContext(final HttpServletRequest request) {
        final var sessionId = sessionCookieResolver.readCookie(request);
        if (sessionId != null) {
            SecurityContext securityContext = securityContextStore.load(sessionId);
            if (securityContext != null) {
                LOGGER.debug("Load SecurityContext from store. Found SecurityContext for session id {} ( request to {} )", sessionId, request.getServletPath());
                return () -> securityContext;
            } else {
                LOGGER.debug("Load SecurityContext from store. No SecurityContext found for session id {}. Creating empty SecurityContext ( request to {} )", sessionId, request.getServletPath());
            }
        }
        return SecurityContextHolder::createEmptyContext;
    }

    @Override
    public void saveContext(final SecurityContext context,
                            final HttpServletRequest request,
                            final HttpServletResponse response) {
        var sessionId = sessionCookieResolver.readCookie(request);
        if (sessionId != null) {
            LOGGER.debug("Save SecurityContext to store. Using exiting session id {} ( request to {} )", sessionId, request.getServletPath());
        } else {
            sessionId = UUID.randomUUID().toString();
            LOGGER.debug("Save SecurityContext to store. Generating new session id {} ( request to {} )", sessionId, request.getServletPath());
            sessionCookieResolver.addCookie(response, sessionId);
        }
        securityContextStore.save(sessionId, context);
    }

    @Override
    public boolean containsContext(final HttpServletRequest request) {
        final var sessionId = sessionCookieResolver.readCookie(request);
        if (sessionId != null) {
            boolean containsContext = securityContextStore.contains(sessionId);
            LOGGER.debug("Check if store contains SecurityContext. Using exiting session id {} with result '{}' ( request to {} )", sessionId, containsContext, request.getServletPath());
            return containsContext;
        } else {
            LOGGER.debug("Check if store contains SecurityContext. No session id found ( request to {} )", request.getServletPath());
            return false;
        }
    }
}
