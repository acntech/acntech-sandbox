package no.acntech.sandbox.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;

@Configuration(proxyBeanMethods = false)
public class RedisConfig {

    @Bean
    public RedisTemplate<String, OAuth2AuthorizedClient> authorizedClientRedisTemplate(
            final RedisConnectionFactory connectionFactory,
            final ObjectMapper objectMapper) {
        var keyRedisSerializer = RedisSerializer.string();
        var valueRedisSerializer = new Jackson2JsonRedisSerializer<>(objectMapper, OAuth2AuthorizedClient.class);
        var redisTemplate = new RedisTemplate<String, OAuth2AuthorizedClient>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(keyRedisSerializer);
        redisTemplate.setValueSerializer(valueRedisSerializer);
        redisTemplate.setHashKeySerializer(keyRedisSerializer);
        redisTemplate.setHashValueSerializer(valueRedisSerializer);
        return redisTemplate;
    }
}
