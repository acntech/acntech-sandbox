package no.acntech.sandbox.service;

import no.acntech.sandbox.store.Store;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientId;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.util.Assert;

public class RedisOAuth2AuthorizedClientService implements OAuth2AuthorizedClientService {

    private final ClientRegistrationRepository clientRegistrationRepository;
    private final Store<OAuth2AuthorizedClientId, OAuth2AuthorizedClient> oAuth2AuthorizedClientStore;

    public RedisOAuth2AuthorizedClientService(final ClientRegistrationRepository clientRegistrationRepository,
                                              final Store<OAuth2AuthorizedClientId, OAuth2AuthorizedClient> oAuth2AuthorizedClientStore) {
        this.clientRegistrationRepository = clientRegistrationRepository;
        this.oAuth2AuthorizedClientStore = oAuth2AuthorizedClientStore;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends OAuth2AuthorizedClient> T loadAuthorizedClient(String clientRegistrationId, String principalName) {
        Assert.hasText(clientRegistrationId, "clientRegistrationId cannot be empty");
        Assert.hasText(principalName, "principalName cannot be empty");
        final var registration = clientRegistrationRepository.findByRegistrationId(clientRegistrationId);
        if (registration == null) {
            return null;
        }
        return (T) oAuth2AuthorizedClientStore.load(new OAuth2AuthorizedClientId(clientRegistrationId, principalName));
    }

    @Override
    public void saveAuthorizedClient(final OAuth2AuthorizedClient authorizedClient, final Authentication principal) {
        Assert.notNull(authorizedClient, "authorizedClient cannot be null");
        Assert.notNull(principal, "principal cannot be null");
        oAuth2AuthorizedClientStore.save(new OAuth2AuthorizedClientId(
                authorizedClient.getClientRegistration().getRegistrationId(), principal.getName()), authorizedClient);
    }

    @Override
    public void removeAuthorizedClient(String clientRegistrationId, String principalName) {
        Assert.hasText(clientRegistrationId, "clientRegistrationId cannot be empty");
        Assert.hasText(principalName, "principalName cannot be empty");
        final var registration = clientRegistrationRepository.findByRegistrationId(clientRegistrationId);
        if (registration != null) {
            oAuth2AuthorizedClientStore.remove(new OAuth2AuthorizedClientId(clientRegistrationId, principalName));
        }
    }
}
