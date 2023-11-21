package no.acntech.sandbox.service;

import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.util.Assert;
import reactor.core.publisher.Mono;

public class RedisOAuth2AuthorizedClientService implements ReactiveOAuth2AuthorizedClientService {

    private final ReactiveClientRegistrationRepository clientRegistrationRepository;
    private final ReactiveRedisTemplate<String, OAuth2AuthorizedClient> redisTemplate;

    public RedisOAuth2AuthorizedClientService(final ReactiveClientRegistrationRepository clientRegistrationRepository,
                                              final ReactiveRedisTemplate<String, OAuth2AuthorizedClient> redisTemplate) {
        this.clientRegistrationRepository = clientRegistrationRepository;
        this.redisTemplate = redisTemplate;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends OAuth2AuthorizedClient> Mono<T> loadAuthorizedClient(String clientRegistrationId, String principalName) {
        Assert.hasText(clientRegistrationId, "clientRegistrationId cannot be empty");
        Assert.hasText(principalName, "principalName cannot be empty");
        return Mono.just(clientRegistrationId)
                .flatMap(clientRegistrationRepository::findByRegistrationId)
                .map(registration -> clientRegistrationId + "." + principalName)
                .flatMap(key -> redisTemplate
                        .opsForValue().get(key))
                .map(oAuth2AuthorizedClient -> (T) oAuth2AuthorizedClient);
    }

    @Override
    public Mono<Void> saveAuthorizedClient(final OAuth2AuthorizedClient authorizedClient, final Authentication authentication) {
        Assert.notNull(authorizedClient, "authorizedClient cannot be null");
        Assert.notNull(authentication, "authentication cannot be null");
        return Mono.just(authorizedClient)
                .map(OAuth2AuthorizedClient::getClientRegistration)
                .map(ClientRegistration::getRegistrationId)
                .map(clientRegistrationId -> clientRegistrationId + "." + authentication.getName())
                .flatMap(authorizedClientId -> redisTemplate
                        .opsForValue()
                        .set(authorizedClientId, authorizedClient))
                .then(Mono.empty());
    }

    @Override
    public Mono<Void> removeAuthorizedClient(String clientRegistrationId, String principalName) {
        Assert.hasText(clientRegistrationId, "clientRegistrationId cannot be empty");
        Assert.hasText(principalName, "principalName cannot be empty");
        return clientRegistrationRepository.findByRegistrationId(clientRegistrationId)
                .map((clientRegistration) -> clientRegistrationId + "." + principalName)
                .doOnNext(redisTemplate::delete)
                .then(Mono.empty());
    }
}
