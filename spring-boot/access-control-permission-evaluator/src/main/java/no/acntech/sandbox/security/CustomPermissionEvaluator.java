package no.acntech.sandbox.security;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;
import java.util.Arrays;

public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if (authentication != null && targetDomainObject instanceof String && permission instanceof String) {
            return hasPermission(targetDomainObject.toString(), permission.toString());
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        if (authentication != null && targetType != null && permission instanceof String) {
            return hasPermission(targetType, permission.toString());
        }
        return false;
    }

    @SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
    private boolean hasPermission(String action, String role) {
        switch (action) {
            case "READ":
                return Arrays.asList("USER").contains(role);
            case "WRITE":
                return Arrays.asList("USER", "ADMIN").contains(role);
            default:
                return false;
        }
    }
}
