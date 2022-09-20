package no.acntech.sandbox.store;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemorySecurityContextStore implements Store<String, SecurityContext> {

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
