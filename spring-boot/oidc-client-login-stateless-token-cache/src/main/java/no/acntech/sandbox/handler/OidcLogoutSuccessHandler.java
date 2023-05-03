package no.acntech.sandbox.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import no.acntech.sandbox.resolver.CookieResolver;
import no.acntech.sandbox.store.OAuth2AuthorizedClientStore;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;

public class OidcLogoutSuccessHandler implements LogoutSuccessHandler {

    private static final CookieResolver SESSION_COOKIE_RESOLVER = CookieResolver.sessionCookieResolver();
    private final OidcClientInitiatedLogoutSuccessHandler delegate;
    private final OAuth2AuthorizedClientStore oAuth2AuthorizedClientStore;

    public OidcLogoutSuccessHandler(final ClientRegistrationRepository clientRegistrationRepository,
                                    final OAuth2AuthorizedClientStore oAuth2AuthorizedClientStore) {
        this.delegate = new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
        this.oAuth2AuthorizedClientStore = oAuth2AuthorizedClientStore;
    }

    @Override
    public void onLogoutSuccess(final HttpServletRequest request,
                                final HttpServletResponse response,
                                final Authentication authentication) throws IOException, ServletException {
        var sessionId = SESSION_COOKIE_RESOLVER.readCookie(request);
        if (sessionId != null) {
            //oAuth2AuthorizedClientStore.remove(sessionId); // TODO: Remove everything on logout
        }
        SESSION_COOKIE_RESOLVER.removeCookie(response);
        delegate.onLogoutSuccess(request, response, authentication);
    }

    public void setPostLogoutRedirectUri(String postLogoutRedirectUri) {
        delegate.setPostLogoutRedirectUri(postLogoutRedirectUri);
    }
}
