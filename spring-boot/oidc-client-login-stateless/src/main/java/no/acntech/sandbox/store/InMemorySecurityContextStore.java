package no.acntech.sandbox.store;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Validated
@Component
public class InMemorySecurityContextStore implements SecurityContextStore {

    private final Map<String, SecurityContext> securityContextStore;

    public InMemorySecurityContextStore() {
        this.securityContextStore = new ConcurrentHashMap<>();
    }

    @Override
    public SecurityContext load(String key) {
        return securityContextStore.get(key);
    }

    @Override
    public void save(String key, SecurityContext securityContext) {
        securityContextStore.put(key, securityContext);
    }

    @Override
    public void remove(String key) {
        securityContextStore.remove(key);
    }

    @Override
    public boolean contains(String key) {
        return securityContextStore.containsKey(key);
    }
}
