package no.acntech.sandbox.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.util.Assert;

public class RedisOAuth2AuthorizedClientService implements OAuth2AuthorizedClientService {

    private final ClientRegistrationRepository clientRegistrationRepository;
    private final RedisTemplate<String, OAuth2AuthorizedClient> redisTemplate;

    public RedisOAuth2AuthorizedClientService(final ClientRegistrationRepository clientRegistrationRepository,
                                              final RedisTemplate<String, OAuth2AuthorizedClient> redisTemplate) {
        this.clientRegistrationRepository = clientRegistrationRepository;
        this.redisTemplate = redisTemplate;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends OAuth2AuthorizedClient> T loadAuthorizedClient(String clientRegistrationId, String principalName) {
        Assert.hasText(clientRegistrationId, "clientRegistrationId cannot be empty");
        Assert.hasText(principalName, "principalName cannot be empty");
        final var clientRegistration = clientRegistrationRepository.findByRegistrationId(clientRegistrationId);
        if (clientRegistration == null) {
            return null;
        }
        var key = clientRegistrationId + "." + principalName;
        return (T) redisTemplate
                .opsForValue()
                .get(key);
    }

    @Override
    public void saveAuthorizedClient(final OAuth2AuthorizedClient authorizedClient, final Authentication authentication) {
        Assert.notNull(authorizedClient, "authorizedClient cannot be null");
        Assert.notNull(authentication, "authentication cannot be null");
        var clientRegistrationId = authorizedClient.getClientRegistration().getRegistrationId();
        var principalName = authentication.getName();
        var key = clientRegistrationId + "." + principalName;
        redisTemplate
                .opsForValue()
                .set(key, authorizedClient);
    }

    @Override
    public void removeAuthorizedClient(String clientRegistrationId, String principalName) {
        Assert.hasText(clientRegistrationId, "clientRegistrationId cannot be empty");
        Assert.hasText(principalName, "principalName cannot be empty");
        final var clientRegistration = clientRegistrationRepository.findByRegistrationId(clientRegistrationId);
        if (clientRegistration != null) {
            var key = clientRegistrationId + "." + principalName;
            redisTemplate.delete(key);
        }
    }
}
