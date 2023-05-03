package no.acntech.sandbox.store;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Component;

@Component
public class RedisSecurityContextStore implements SecurityContextStore {

    private final RedisTemplate<String, SecurityContext> redisTemplate;

    public RedisSecurityContextStore(final RedisTemplate<String, SecurityContext> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public SecurityContext load(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void save(String key, SecurityContext value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void remove(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public boolean contains(String key) {
        var hasKey = redisTemplate.hasKey(key);
        return hasKey != null && hasKey;
    }
}
