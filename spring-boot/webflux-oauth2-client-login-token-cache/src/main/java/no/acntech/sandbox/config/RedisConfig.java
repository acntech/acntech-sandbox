package no.acntech.sandbox.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;

@Configuration(proxyBeanMethods = false)
public class RedisConfig {

    @Bean
    public ReactiveRedisTemplate<String, OAuth2AuthorizedClient> authorizedClientRedisTemplate(
            final ObjectMapper objectMapper,
            final ReactiveRedisConnectionFactory connectionFactory) {
        var keyRedisSerializer = RedisSerializer.string();
        var valueRedisSerializer = new Jackson2JsonRedisSerializer<>(objectMapper, OAuth2AuthorizedClient.class);
        var serializationContext = RedisSerializationContext.<String, OAuth2AuthorizedClient>newSerializationContext()
                .key(keyRedisSerializer)
                .value(valueRedisSerializer)
                .hashKey(keyRedisSerializer)
                .hashValue(valueRedisSerializer)
                .build();
        return new ReactiveRedisTemplate<>(connectionFactory, serializationContext);
    }
}
