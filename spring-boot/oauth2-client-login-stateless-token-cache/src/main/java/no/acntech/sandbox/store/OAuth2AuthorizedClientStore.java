package no.acntech.sandbox.store;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientId;

public interface OAuth2AuthorizedClientStore extends Store<OAuth2AuthorizedClientId, OAuth2AuthorizedClient> {
}
