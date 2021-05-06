package no.acntech.sandbox.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import no.acntech.sandbox.resolver.CookieResolver;
import no.acntech.sandbox.store.InMemorySecurityContextStore;

import static no.acntech.sandbox.repository.InMemorySecurityContextRepository.SESSION_COOKIE_RESOLVER;

public class OidcLogoutSuccessHandler implements LogoutSuccessHandler {

    private final OidcClientInitiatedLogoutSuccessHandler delegate;
    private final InMemorySecurityContextStore inMemorySecurityContextStore;
    private final Set<String> deleteCookies = new HashSet<>();

    public OidcLogoutSuccessHandler(final ClientRegistrationRepository clientRegistrationRepository,
                                    final InMemorySecurityContextStore inMemorySecurityContextStore) {
        delegate = new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
        this.inMemorySecurityContextStore = inMemorySecurityContextStore;
    }

    @Override
    public void onLogoutSuccess(final HttpServletRequest request,
                                final HttpServletResponse response,
                                final Authentication authentication) throws IOException, ServletException {
        deleteCookies(response);
        clearSecurityContextStore(request);
        delegate.onLogoutSuccess(request, response, authentication);
    }

    public void setPostLogoutRedirectUri(String postLogoutRedirectUri) {
        delegate.setPostLogoutRedirectUri(postLogoutRedirectUri);
    }

    public void setDeleteCookies(String... deleteCookies) {
        Assert.notEmpty(deleteCookies, "Array can not be null or empty");
        this.deleteCookies.addAll(Arrays.asList(deleteCookies));
    }

    private void deleteCookies(final HttpServletResponse response) {
        CookieResolver cookieResolver = new CookieResolver();
        deleteCookies.forEach(name -> {
            cookieResolver.setCookieName(name);
            cookieResolver.removeCookie(response);
        });
    }

    private void clearSecurityContextStore(final HttpServletRequest request) {
        String sessionId = SESSION_COOKIE_RESOLVER.readCookie(request);
        if (sessionId != null) {
            inMemorySecurityContextStore.deleteSecurityContext(sessionId);
        }
    }
}
