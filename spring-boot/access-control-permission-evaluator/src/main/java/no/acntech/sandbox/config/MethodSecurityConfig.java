package no.acntech.sandbox.config;

import no.acntech.sandbox.security.CustomPermissionEvaluator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableMethodSecurity
@Configuration(proxyBeanMethods = false)
public class MethodSecurityConfig {

    @Bean
    public MethodSecurityExpressionHandler methodSecurityExpressionHandler() {
        final var securityExpressionHandler = new DefaultMethodSecurityExpressionHandler();
        securityExpressionHandler.setPermissionEvaluator(new CustomPermissionEvaluator());
        return securityExpressionHandler;
    }
}
