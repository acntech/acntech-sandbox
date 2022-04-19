package no.acntech.sandbox.store;

import org.springframework.security.core.context.SecurityContext;

public interface SecurityContextStore {

    SecurityContext loadContext(String key);

    void saveContext(String key, SecurityContext securityContext);

    void removeContext(String key);

    boolean containsContext(String key);
}
