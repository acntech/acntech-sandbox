package no.acntech.sandbox.store;

import org.springframework.security.core.context.SecurityContext;

import java.util.HashMap;
import java.util.Map;

public class InMemorySecurityContextStore implements SecurityContextStore {

    private static final Map<String, SecurityContext> SECURITY_CONTEXT_STORE = new HashMap<>();

    @Override
    public SecurityContext loadContext(String key) {
        return SECURITY_CONTEXT_STORE.get(key);
    }

    @Override
    public void saveContext(String key, SecurityContext securityContext) {
        SECURITY_CONTEXT_STORE.put(key, securityContext);
    }

    @Override
    public void removeContext(String key) {
        SECURITY_CONTEXT_STORE.remove(key);
    }

    @Override
    public boolean containsContext(String key) {
        return SECURITY_CONTEXT_STORE.containsKey(key);
    }
}
