package no.acntech.sandbox.store;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientId;
import org.springframework.stereotype.Component;

@Component
public class RedisOAuth2AuthorizedClientStore implements OAuth2AuthorizedClientStore {

    private final RedisTemplate<OAuth2AuthorizedClientId, OAuth2AuthorizedClient> redisTemplate;

    public RedisOAuth2AuthorizedClientStore(final RedisTemplate<OAuth2AuthorizedClientId, OAuth2AuthorizedClient> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public OAuth2AuthorizedClient load(final OAuth2AuthorizedClientId key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void save(final OAuth2AuthorizedClientId key, final OAuth2AuthorizedClient value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void remove(final OAuth2AuthorizedClientId key) {
        redisTemplate.delete(key);
    }

    @Override
    public boolean contains(final OAuth2AuthorizedClientId key) {
        var hasKey = redisTemplate.hasKey(key);
        return hasKey != null && hasKey;
    }
}
