package no.acntech.sandbox.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.ModifiedExpiryPolicy;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@EnableCaching
@Configuration
public class CacheConfig {

    @Bean
    public JCacheManagerCustomizer jCacheManagerCustomizer(@Value("${app.cache.default-time-to-live}") Duration cacheTimeToLive) {
        final var duration = new javax.cache.expiry.Duration(TimeUnit.SECONDS, cacheTimeToLive.getSeconds());
        return cacheManager -> {
            cacheManager.destroyCache("greetings");
            cacheManager.createCache("greetings", new MutableConfiguration<>()
                    .setExpiryPolicyFactory(ModifiedExpiryPolicy.factoryOf(duration)));
        };
    }
}
