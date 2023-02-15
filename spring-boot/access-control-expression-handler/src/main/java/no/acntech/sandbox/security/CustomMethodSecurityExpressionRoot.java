package no.acntech.sandbox.security;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

import java.util.Arrays;

public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private Object filterObject;
    private Object returnObject;

    public CustomMethodSecurityExpressionRoot(final Authentication authentication) {
        super(authentication);
    }

    @SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
    public boolean hasAccess(String action, String role) {
        if (action != null && role != null) {
            switch (action) {
                case "READ" -> {
                    return Arrays.asList("USER").contains(role);
                }
                case "WRITE" -> {
                    return Arrays.asList("USER", "ADMIN").contains(role);
                }
            }
        }
        return false;
    }

    @Override
    public void setFilterObject(Object filterObject) {
        this.filterObject = filterObject;
    }

    @Override
    public Object getFilterObject() {
        return filterObject;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    @Override
    public Object getReturnObject() {
        return returnObject;
    }

    @Override
    public Object getThis() {
        return this;
    }
}
