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
    public SecurityContext loadContext(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void saveContext(String key, SecurityContext securityContext) {
        redisTemplate.opsForValue().set(key, securityContext);
    }

    @Override
    public void removeContext(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public boolean containsContext(String key) {
        var hasKey = redisTemplate.hasKey(key);
        return hasKey != null && hasKey;
    }
}
