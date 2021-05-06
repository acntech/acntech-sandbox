package no.acntech.sandbox.store;

import org.springframework.security.core.context.SecurityContext;

public interface SecurityContextStore {

    SecurityContext loadSecurityContext(String key);

    void saveSecurityContext(String key, SecurityContext securityContext);

    void deleteSecurityContext(String key);

    boolean containsSecurityContext(String key);
}
