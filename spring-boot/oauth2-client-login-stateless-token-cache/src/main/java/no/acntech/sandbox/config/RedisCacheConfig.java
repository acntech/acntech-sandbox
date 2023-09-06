package no.acntech.sandbox.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientId;

@Configuration(proxyBeanMethods = false)
public class RedisCacheConfig {

    @Bean
    public RedisTemplate<OAuth2AuthorizedClientId, OAuth2AuthorizedClient> redisTemplate(final RedisConnectionFactory connectionFactory) {
        var redisTemplate = new RedisTemplate<OAuth2AuthorizedClientId, OAuth2AuthorizedClient>();
        redisTemplate.setConnectionFactory(connectionFactory);
        return redisTemplate;
    }
}
