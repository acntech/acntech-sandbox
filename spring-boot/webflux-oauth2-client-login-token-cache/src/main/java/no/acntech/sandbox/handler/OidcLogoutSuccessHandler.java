package no.acntech.sandbox.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.oidc.web.server.logout.OidcClientInitiatedServerLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import reactor.core.publisher.Mono;

public class OidcLogoutSuccessHandler implements ServerLogoutSuccessHandler {

    private final OidcClientInitiatedServerLogoutSuccessHandler delegate;

    public OidcLogoutSuccessHandler(final ReactiveClientRegistrationRepository clientRegistrationRepository) {
        this.delegate = new OidcClientInitiatedServerLogoutSuccessHandler(clientRegistrationRepository);
    }

    @Override
    public Mono<Void> onLogoutSuccess(final WebFilterExchange exchange,
                                      final Authentication authentication) {
        // TODO: Delete current OAuth2AuthorizedClient from Redis cache
        return delegate.onLogoutSuccess(exchange, authentication);
    }

    public void setPostLogoutRedirectUri(String postLogoutRedirectUri) {
        delegate.setPostLogoutRedirectUri(postLogoutRedirectUri);
    }
}
