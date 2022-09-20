package no.acntech.sandbox.handler;

import no.acntech.sandbox.resolver.CookieResolver;
import no.acntech.sandbox.store.Store;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OidcLogoutSuccessHandler implements LogoutSuccessHandler {

    private final OidcClientInitiatedLogoutSuccessHandler delegate;
    private final CookieResolver sessionCookieResolver;
    private final Store<String, SecurityContext> securityContextStore;

    public OidcLogoutSuccessHandler(final ClientRegistrationRepository clientRegistrationRepository,
                                    final CookieResolver sessionCookieResolver,
                                    final Store<String, SecurityContext> securityContextStore) {
        delegate = new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
        this.sessionCookieResolver = sessionCookieResolver;
        this.securityContextStore = securityContextStore;
    }

    @Override
    public void onLogoutSuccess(final HttpServletRequest request,
                                final HttpServletResponse response,
                                final Authentication authentication) throws IOException, ServletException {
        var sessionId = sessionCookieResolver.readCookie(request);
        if (sessionId != null) {
            securityContextStore.remove(sessionId);
        }
        sessionCookieResolver.removeCookie(response);
        delegate.onLogoutSuccess(request, response, authentication);
    }

    public void setPostLogoutRedirectUri(String postLogoutRedirectUri) {
        delegate.setPostLogoutRedirectUri(postLogoutRedirectUri);
    }
}
