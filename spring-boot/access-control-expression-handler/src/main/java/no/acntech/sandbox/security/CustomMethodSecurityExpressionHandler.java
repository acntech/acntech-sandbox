package no.acntech.sandbox.security;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;

public class CustomMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {

    private final AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(final Authentication authentication,
                                                                              final MethodInvocation invocation) {
        final var securityExpressionRoot = new CustomMethodSecurityExpressionRoot(authentication);
        securityExpressionRoot.setPermissionEvaluator(getPermissionEvaluator());
        securityExpressionRoot.setTrustResolver(trustResolver);
        securityExpressionRoot.setRoleHierarchy(getRoleHierarchy());
        return securityExpressionRoot;
    }
}
