package no.acntech.sandbox.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import no.acntech.sandbox.resolver.CookieResolver;
import no.acntech.sandbox.store.Store;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;

public class OidcLogoutSuccessHandler implements LogoutSuccessHandler {

    private static final CookieResolver SESSION_COOKIE_RESOLVER = CookieResolver.sessionCookieResolver();
    private final OidcClientInitiatedLogoutSuccessHandler delegate;
    private final Store<String, SecurityContext> securityContextStore;

    public OidcLogoutSuccessHandler(final ClientRegistrationRepository clientRegistrationRepository,
                                    final Store<String, SecurityContext> securityContextStore) {
        delegate = new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
        this.securityContextStore = securityContextStore;
    }

    @Override
    public void onLogoutSuccess(final HttpServletRequest request,
                                final HttpServletResponse response,
                                final Authentication authentication) throws IOException, ServletException {
        var sessionId = SESSION_COOKIE_RESOLVER.readCookie(request);
        if (sessionId != null) {
            securityContextStore.remove(sessionId);
        }
        SESSION_COOKIE_RESOLVER.removeCookie(response);
        delegate.onLogoutSuccess(request, response, authentication);
    }

    public void setPostLogoutRedirectUri(String postLogoutRedirectUri) {
        delegate.setPostLogoutRedirectUri(postLogoutRedirectUri);
    }
}
